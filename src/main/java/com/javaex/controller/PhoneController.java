package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PhoneVo;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {
	// 필드

	// 생성자

	// 메소드g/s

	/** 메소드 일반***메소드 마다 기능 1개씩-->기능마다 url 부여 */
	// 테스트

	// 리스트
	@RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
	public String List(Model model) {//model은 리스트의 주소를 옮기는 상자이다.
		System.out.println("list");
		
		//dao를 톨해 리스트을 가져온다.
		PhoneDao phoneDao = new PhoneDao();
		List<PhoneVo> phoneList=phoneDao.getpersonList();
		
		//model-->데이터를 옮기는 방법-->담으면 된다
		model.addAttribute("plist",phoneList);
		//모델을 어트리뷰트로 옮긴다.
		
		System.out.println(phoneList.toString());
		
		return "/WEB-INF/views/list.jsp";
	}

	// 등록폼
	@RequestMapping(value = "/writeForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String writeFoem() {
		System.out.println("등록폼입니다.");
		return "/WEB-INF/views/writeForm.jsp";
		//내부에 파일을 찾는 포워드 방식이다
	}
	//http://localhost:8088/phonebook3/phone/write?name=김우진&hp=010-4567-4567&company=02-4567-4567
	// 등록
	@RequestMapping(value = "/write",method = {RequestMethod.GET,RequestMethod.POST})
	public String write(@RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company) {
		System.out.println("write");
		PhoneVo phoneVo =new PhoneVo(name,hp,company);
		System.out.println(phoneVo.toString());
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.personInsert(phoneVo);
		
		return "redirect:/phone/list";
		//이주소로 보내라는 리다이렉트 방식이다.
	}
	// 수정폼-->modifyForm
	@RequestMapping(value = "/modifyForm",method = {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(Model model,@RequestParam("id") int personId) {
		System.out.println("수정폼입니다.");
		PhoneDao phoneDao = new PhoneDao();
		PhoneVo pVo =phoneDao.getPerson(personId);
		model.addAttribute("personVo",pVo);
		return "/WEB-INF/views/updateForm.jsp";
	}
	// 수정-->modify
	@RequestMapping(value = "/modify",method = {RequestMethod.GET,RequestMethod.POST})
	public String modify(@RequestParam("id") int personid, @RequestParam("name") String name, @RequestParam("hp") String hp,
			@RequestParam("company") String company) {
		PhoneVo phoneVo = new PhoneVo(personid, name, hp, company);
		PhoneDao phoneDao =new PhoneDao();
		phoneDao.personUpdate(phoneVo);
		
		
		return "redirect:/phone/list";
	}
	// 삭제-->delete
	@RequestMapping(value = "/delete",method = {RequestMethod.GET,RequestMethod.POST})
	public String delete(@RequestParam("id") int id) {
		System.out.println("삭제입니다.");
		PhoneDao phoneDao =new PhoneDao();
		phoneDao.persondelete(id);
		return "redirect:/phone/list";
	}
}
