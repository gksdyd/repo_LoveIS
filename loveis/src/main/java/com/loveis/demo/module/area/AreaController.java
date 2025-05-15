package com.loveis.demo.module.area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveis.demo.module.base.BaseController;

@Controller
@RequestMapping(value = "/love/area")
public class AreaController extends BaseController {

	@Autowired
	AreaService areaService;
	
	@ResponseBody
	@RequestMapping(value = "/AreaLoveProc")
	public void areaLoveProc(AreaDto dto) {
		areaService.update(dto);
	}
	
	@RequestMapping(value = "/AreaLoveSelectProc")
	public String areaLoveSelectProc(AreaVo vo, Model model) {
		model.addAttribute("list", areaService.selectListLocal(vo));
		return "love/include/signUpFormLocal";
	}
}
