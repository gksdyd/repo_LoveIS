package com.loveis.demo.module.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/love/index")
public class IndexLoveController {

	@RequestMapping(value = "/IndexLoveView")
	public String indexLoveView() {
		return "love/index/IndexLoveView";
	}
}
