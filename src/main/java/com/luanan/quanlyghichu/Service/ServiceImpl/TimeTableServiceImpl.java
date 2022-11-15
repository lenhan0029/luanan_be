package com.luanan.quanlyghichu.Service.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.luanan.quanlyghichu.Model.DTO.Request.TimeTableDTO;
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
			return ResponseEntity.ok().body(
					new ResponseModel("Lấy thời khóa biểu thành công",200));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseModel("Tài khoản không tồn tại",404));
	}

	public boolean getTimeTableData(String username, String password,TimeTable timetable) {
		System.setProperty("webdriver.chrome.driver","D:\\Drivers\\chromedriver.exe");
	    WebDriver driver = new ChromeDriver();

	    // Navigate to URL
	    driver.get("http://thongtindaotao.sgu.edu.vn/");
	    WebElement u =driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_ucDangNhap_txtTaiKhoa"));
	    WebElement p=driver.findElement(By.id("ctl00_ContentPlaceHolder1_ctl00_ucDangNhap_txtMatKhau"));
	    WebElement login=driver.findElement(By.name("ctl00$ContentPlaceHolder1$ctl00$ucDangNhap$btnDangNhap"));
	    u.sendKeys(username);
	    p.sendKeys(password);
	    login.click();
	    // Read page content
	    driver.get("http://thongtindaotao.sgu.edu.vn/default.aspx?page=thoikhoabieu&sta=1");
	    String content = driver.getPageSource();
	        List <WebElement> totalTable = driver.findElements(By.xpath("//div[@class='grid-roll2']/table[@class='body-table']/tbody/tr/td"));
//	    String content = driver.getCurrentUrl();
	        List<String> list = new ArrayList<String>();
	        for (WebElement webElement : totalTable) {
	            list.add(webElement.getText());
	        }

	    // Print the page content
	        List<String> listSubject = new ArrayList<String>();
	        
	        String day = "Hai Ba Tư Năm Sáu Bảy";
	        while(list.size() != 0) {
	        	int step = 1;
	        	int i = 0;

    			
	        	while(i < list.size()){
	        		String t = null;
	        		StringTokenizer st = new StringTokenizer(list.get(i),"\n");
	        		int count = st.countTokens();
	        		if(count != 0) {
//	        			System.out.println("Hello " + count);
//	        			System.out.println("hi " + list.get(i));
	        			t = st.nextToken();
	        		}
	        		if(i < 6) {
		        		listSubject.add(list.get(i));
		        		i +=1;
		        	}else {
		        		if(list.get(i).equals("") || list.get(i).equals("01") || list.get(i).equals("x")) {
		        			i +=1;
		        		continue;
		        		}else if(t != null && day.contains(t)) {
		        			
		        			if(step == 1 && st.hasMoreTokens()) {
//		        				System.out.println("Hello");
		        				String t1 = st.nextToken();
		        				int num = 1;
			        			while(num < count && day.contains(t1)) {
//			        				System.out.println("Hi");
			        				step+=1;
			        				num +=1;
			        				if(num < count)
			        				t1 = st.nextToken();
			        			}
		        			}
		        			StringTokenizer d = new StringTokenizer(list.get(i),"\n");
		        			StringTokenizer s = new StringTokenizer(list.get(i+1),"\n");
		        			StringTokenizer n = new StringTokenizer(list.get(i+2),"\n");
		        			StringTokenizer r = new StringTokenizer(list.get(i+3),"\n");
		        			for(int x = 0; x < step; x++) {
		        				Subject subject = new Subject();
			        			subject.setCode(listSubject.get(0));
			        			subject.setName(listSubject.get(1));
			        			subject.setSubjectGroup(listSubject.get(2));
			        			subject.setStc(Integer.parseInt(listSubject.get(3)));
			        			subject.setClassCode(listSubject.get(4));
			        			if(x==0) {
			        				subject.setPractise(true);
			        			}
			        			subject.setDay(d.nextToken());
			        			subject.setStart(Integer.parseInt(s.nextToken()));
			        			subject.setNumber(Integer.parseInt(n.nextToken()));
			        			subject.setRoom(r.nextToken());
//			        			System.out.println(subject.toString());
			        			subject.setTimetable(timetable);
			        			subjectRepository.save(subject);
//			        			System.out.println("steppppppppppppppppppppppppppppp" + step);
		        			}
//		        			System.out.println("hi" +i);
		        			i+=6;
		        			
		        			
		        		}else if(t != null && t.equals("DSSV")) {
//		        			System.out.println("hi" +i);
		        			int e = 0;
		        			while(e <= i) {
		        				list.remove(0);
		        				e++;
		        			}
//		        			System.out.println(list.toString());
		        			listSubject.removeAll(listSubject);
//		        			System.out.println(listSubject.toString());
		        			i=0;
		        			step=1;
		        			
		        		}
		        		
		        	}
	        	}
	        }
//	    System.out.println(totalTable.toString());
	    WebElement logout=driver.findElement(By.id("ctl00_Header1_Logout1_lbtnLogOut"));
	    logout.click();
	    // Close driver 
	    driver.quit();
	    return true;
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
}
