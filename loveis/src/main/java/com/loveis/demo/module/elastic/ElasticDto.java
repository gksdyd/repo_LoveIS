package com.loveis.demo.module.elastic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ElasticDto {

	private List<String> contents;
	private String index;
	private String id;
	private String name;
	private String engName;
	private String url;

	public List<String> getContents() {
		return contents;
	}

	public void setContents(String[] contents) {
		this.contents = Arrays.stream(contents)
                .filter(s -> !s.isBlank())
                .collect(Collectors.toList());
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public void setContents(List<String> contents) {
		this.contents = contents;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
