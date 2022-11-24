package com.luanan.quanlyghichu.Service.ServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.hibernate.type.OffsetDateTimeType;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.luanan.quanlyghichu.Model.DTO.Request.NoteDTO;
import com.luanan.quanlyghichu.Model.DTO.Response.ResponseModel;
import com.luanan.quanlyghichu.Model.Entities.Account;
import com.luanan.quanlyghichu.Model.Entities.Note;
import com.luanan.quanlyghichu.Repository.AccountRepository;
import com.luanan.quanlyghichu.Repository.NoteRepository;
import com.luanan.quanlyghichu.Service.NoteService;

@Service
public class NoteServiceImpl implements NoteService{
	
	final NoteRepository noteRepository;
	final AccountRepository accountRepository;
	final ModelMapper modelMapper;

	public NoteServiceImpl(NoteRepository noteRepository, AccountRepository accountRepository,
			ModelMapper modelMapper) {
		super();
		this.noteRepository = noteRepository;
		this.accountRepository = accountRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<?> createNote(NoteDTO dto) {
		Optional<Account> account = accountRepository.findById(dto.getId_account());
		if(account.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Tài khoản không tồn tại",404));
		}
		Note note = new Note();
		note.setDescription(dto.getDescription());
		note.setLocation(dto.getLocation());
		note.setTitle(dto.getTitle());
		note.setAccount(account.get());
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	    OffsetDateTime st = OffsetDateTime.now();
	    if(dto.getTimeStart() != "") {
			st = OffsetDateTime.parse(dto.getTimeStart(),dateTimeFormatter);
			System.out.println(st.toString());
			
		}
		note.setTimeStart(st);
		if(dto.getTimeEnd() != "") {
			st = OffsetDateTime.parse(dto.getTimeEnd(),dateTimeFormatter);
			note.setTimeEnd(st);
			
		}
		Note newNote = noteRepository.save(note);
		if(newNote != null) {
			return ResponseEntity.ok().body(
					new ResponseModel("Tạo ghi chú thành công",200,newNote));
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
				new ResponseModel("Tài khoản không tồn tại",403));
	}

	@Override
	public ResponseEntity<?> updateNote(int id, NoteDTO dto) {
		Optional<Note> oldNote = noteRepository.findById(id);
		if(oldNote.isEmpty() || oldNote.get().getAccount().getId() != dto.getId_account()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy ghi chú",404));
		}
		Note temp = oldNote.get();
		temp.setTitle(dto.getTitle());
		temp.setDescription(dto.getDescription());
		temp.setLocation(dto.getLocation());
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	    OffsetDateTime st = OffsetDateTime.now();
	    if(dto.getTimeStart() != "") {
			st = OffsetDateTime.parse(dto.getTimeStart(),dateTimeFormatter);
			System.out.println(st.toString());
			
		}
		temp.setTimeStart(st);
		if(dto.getTimeEnd() != "") {
			st = OffsetDateTime.parse(dto.getTimeEnd(),dateTimeFormatter);
			temp.setTimeEnd(st);
			
		}
		Note newNote = noteRepository.save(temp);
		return ResponseEntity.ok().body(
				new ResponseModel("Cập nhật ghi chú thành công",200,newNote));
	}

	@Override
	public ResponseEntity<?> deleteNote(int id) {
		Optional<Note> oldNote = noteRepository.findById(id);
		if(oldNote.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy ghi chú",404));
		}
		noteRepository.deleteById(id);
		return ResponseEntity.ok().body(
				new ResponseModel("Xóa ghi chú thành công",200));
	}

	@Override
	public ResponseEntity<?> getNoteByAccount(int id_account,String title, String location, String timeStart) {
		Optional<Account> account = accountRepository.findById(id_account);
		if(account.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Tài khoản không tồn tại",404));
		}
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	    OffsetDateTime st = OffsetDateTime.now();
	    if(timeStart != "0") {
	    	String starttime = timeStart + "+07:00";
			st = OffsetDateTime.parse(starttime,dateTimeFormatter);
			System.out.println(st.toString());
			
		}
	    LocalDateTime s = LocalDateTime.of(st.getYear(), st.getMonth(), st.getDayOfMonth(), 0, 0, 0, 0);
	    LocalDateTime e = LocalDateTime.of(st.getYear(), st.getMonth(), st.getDayOfMonth()+1, 0, 0, 0, 0);
	    ZoneOffset zoneOffSet = ZoneOffset.of("+07:00");
	    OffsetDateTime start = OffsetDateTime.of(s,zoneOffSet);
	    OffsetDateTime end = OffsetDateTime.of(e,zoneOffSet);
		List<Note> notes = noteRepository.listNote(id_account, title, location,start,end);
		if(notes.size() != 0) {
			return ResponseEntity.ok().body(
					new ResponseModel("Lấy ghi chú thành công",200,notes));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseModel("ghi chú không tồn tại",404));
	}

	@Override
	public ResponseEntity<?> getNoteById(int id) {
		Optional<Note> note = noteRepository.findById(id);
		if(note.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("ghi chú không tồn tại",404));
		}
		return ResponseEntity.ok().body(
				new ResponseModel("Lấy ghi chú thành công",200,note.get()));
	}

}
