package com.luanan.quanlyghichu.Service.ServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.luanan.quanlyghichu.Model.DTO.Request.TimeTableDTO;
import com.luanan.quanlyghichu.Model.DTO.Request.UpdateTimeTable;
import com.luanan.quanlyghichu.Model.DTO.Response.ResponseModel;
import com.luanan.quanlyghichu.Model.Entities.Account;
import com.luanan.quanlyghichu.Model.Entities.Subject;
import com.luanan.quanlyghichu.Model.Entities.TimeTable;
import com.luanan.quanlyghichu.Repository.AccountRepository;
import com.luanan.quanlyghichu.Repository.SubjectRepository;
import com.luanan.quanlyghichu.Repository.TimeTableRepsitory;
import com.luanan.quanlyghichu.Service.TimeTableService;

@Service
public class TimeTableServiceImpl implements TimeTableService{
	
	final TimeTableRepsitory timeTableRepsitory;
	final SubjectRepository subjectRepository;
	final AccountRepository accountRepository;

	public TimeTableServiceImpl(TimeTableRepsitory timeTableRepsitory, SubjectRepository subjectRepository,
			AccountRepository accountRepository) {
		super();
		this.timeTableRepsitory = timeTableRepsitory;
		this.subjectRepository = subjectRepository;
		this.accountRepository = accountRepository;
	}

	@Override
	public ResponseEntity<?> createTimeTable(TimeTableDTO dto) {
		// TODO Auto-generated method stub
		Optional<Account> account = accountRepository.findById(dto.getId_account());
		if(account.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Tài khoản không tồn tại",404));
		}
		Optional<TimeTable> timetable = timeTableRepsitory.findByAccount(dto.getId_account());
		if(timetable.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					new ResponseModel("Thời khóa biểu đã tồn tại",409));
		}
		TimeTable newTimeTable = new TimeTable();
		newTimeTable.setUsername(dto.getUsername());
		newTimeTable.setPassword(dto.getPassword());
		newTimeTable.setAccount(account.get());
		TimeTable rs = timeTableRepsitory.save(newTimeTable);
		boolean result = getTimeTableData(dto.getUsername(), dto.getPassword(),rs);
		if(result == true) {
			List<Subject> subjects = subjectRepository.findByTimeTable(rs.getId());
			return ResponseEntity.ok().body(
					new ResponseModel("Lấy thời khóa biểu thành công",200,subjects));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseModel("Tài khoản không tồn tại",404));
	}

	@Override
	public ResponseEntity<?> getTimeTable(int id_account) {
		Optional<Account> account = accountRepository.findById(id_account);
		if(account.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Tài khoản không tồn tại",404));
		}
		Optional<TimeTable> timetable = timeTableRepsitory.findByAccount(id_account);
		if(timetable.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Thời khóa biểu đã tồn tại",404));
		}
		List<Subject> subjects = subjectRepository.findByTimeTable(timetable.get().getId());
		if(subjects.size() != 0) {
			return ResponseEntity.ok().body(
					new ResponseModel("Lấy thời khóa biểu thành công",200,subjects)); 
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseModel("Lấy thời khóa biểu thất bại",404));
	}

	@Override
	public ResponseEntity<?> addNoteToSubject(int id_subject, String note) {
		Optional<Subject> subject = subjectRepository.findById(id_subject);
		if(subject.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Môn học không tồn tại",404));
		}
		Subject temp = subject.get();
		temp.setNote(note);
		Subject newSubject = subjectRepository.save(temp);
		return ResponseEntity.ok().body(
				new ResponseModel("Thêm ghi chú thành công",200,newSubject)); 
	}

	@Override
	public ResponseEntity<?> updateTimeTable(UpdateTimeTable dto) {
		Optional<TimeTable> timetable = timeTableRepsitory.findByAccount(dto.getId_account());
		if(timetable.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Tài khoản không tồn tại thời khóa biểu",404));
		}
		List<Subject> subjects = subjectRepository.findByTimeTable(timetable.get().getId());
		String pass = timetable.get().getPassword();
		if(!dto.getPassword().equals("")) {
			pass = dto.getPassword();
		}
		if(dto.getPassword() != "") {
			boolean result = getTimeTableData(timetable.get().getUsername(),pass,timetable.get());
			if(result == true) {
				if(!subjects.isEmpty()) {
					for (Subject subject : subjects) {
						subjectRepository.delete(subject);
					}
				}
				return ResponseEntity.ok().body(
						new ResponseModel("Cập nhật thời khóa biểu thành công",200));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Cập nhật thời khóa biểu thất bại",404));
		}
		boolean result = getTimeTableData(timetable.get().getUsername(),timetable.get().getPassword(),timetable.get());
		if(result == true) {
			if(!subjects.isEmpty()) {
				for (Subject subject : subjects) {
					subjectRepository.delete(subject);
				}
			}
			return ResponseEntity.ok().body(
					new ResponseModel("Cập nhật thời khóa biểu thành công",200));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseModel("Cập nhật thời khóa biểu thất bại",404));
	}

	public boolean getTimeTableData(String username, String password,TimeTable timetable) {
		
		
		try {
            
            Connection.Response connect = Jsoup.connect("http://thongtindaotao.sgu.edu.vn/").method(Connection.Method.GET)
            .execute();
            Document index = connect.parse();
                FormElement form = index.select("[name=aspnetForm]").forms().get(0);
                Connection post = form.submit();
                Connection.KeyVal un = post.data("ctl00$ContentPlaceHolder1$ctl00$ucDangNhap$txtTaiKhoa");
                un.value(username);
                Connection.KeyVal pw = post.data("ctl00$ContentPlaceHolder1$ctl00$ucDangNhap$txtMatKhau");
                pw.value(password);
                Connection.Response res;
                Map<String, String> loginCookies;
            try {
                res = post.execute();
            } finally {
                }

            loginCookies = connect.cookies();
            Connection.Response conn = Jsoup.connect("http://thongtindaotao.sgu.edu.vn/default.aspx?page=thoikhoabieu&sta=1")
                    .cookies(loginCookies)
                    .method(Connection.Method.GET)
                    .execute();
            Document tkb = conn.parse();
            Elements listElements = tkb.select(".grid-roll2 > .body-table > tbody > tr >td");
            if(listElements.text() == ""){
                FormElement form1 = tkb.select("[name=aspnetForm]").forms().get(0);
                Connection post1 = form1.submit();
                Connection.KeyVal option = post1.data("ctl00$ContentPlaceHolder1$ctl00$ddlChonNHHK");
                option.value("20221");
                Connection.Response res1;
            try {
                res1 = post1.execute();
            } finally {
                }
            tkb = Jsoup.connect("http://thongtindaotao.sgu.edu.vn/default.aspx?page=thoikhoabieu&sta=1")
                    .cookies(loginCookies)
                    .get();
            listElements = tkb.select(".grid-roll2 > .body-table > tbody > tr >td");
            }
            List<String> list = new ArrayList<String>();
	        for (Element Element : listElements) {
	            list.add(Element.text());
	        }

//            System.out.println(list.get(7));
	        List<String> listSubject = new ArrayList<String>();
	        
	        String day = "Hai Ba Tư Năm Sáu Bảy";
	        while(list.size() != 0) {
	        	int step = 0;
	        	
	        	if(day.contains(list.get(8).split(" ")[0])) {
	        		StringTokenizer d = new StringTokenizer(list.get(8));
	        		StringTokenizer s = new StringTokenizer(list.get(9));
	        		StringTokenizer n = new StringTokenizer(list.get(10));
	        		StringTokenizer r = new StringTokenizer(list.get(11));
	        		while(d.hasMoreTokens()) {
	        			Subject subject = new Subject();
	        			subject.setCode(list.get(0));
	        			subject.setName(list.get(1));
	        			subject.setSubjectGroup(list.get(2));
	        			subject.setStc(Integer.parseInt(list.get(3)));
	        			subject.setClassCode(list.get(4));
	        			if(list.get(5)=="01" && step == 0) {
	        				subject.setPractise(true);
	        			}
	        			subject.setDay(d.nextToken());
	        			subject.setStart(Integer.parseInt(s.nextToken()));
	        			subject.setNumber(Integer.parseInt(n.nextToken()));
	        			subject.setRoom(r.nextToken());
	        			subject.setTimetable(timetable);
	        			subjectRepository.save(subject);
	        			System.out.println(subject.toString());
	        			}
	        		step=0;
	        		
	        	}
	        	int temp = 0;
	        	while(temp < 15) {
	        		list.remove(0);
	        		temp++;
	        	}
	        }
	        return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	        
	}

}
