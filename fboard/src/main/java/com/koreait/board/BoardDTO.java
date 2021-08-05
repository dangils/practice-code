package com.koreait.board;

import java.util.ArrayList;
import java.util.List;

public class BoardDTO {
	private int idx;
	private String userid;
	private String title;
	private String content;
	private int hit;
	private int like0;
	private String regdate;
	private String file;

	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getLike() {
		return like0;
	}
	public void setLike(int like0) {
		this.like0 = like0;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "BoardDTO [idx=" + idx + ", userid=" + userid + ", title=" + title + ", content=" + content + ", hit="
				+ hit + ", like=" + like0 + ", regdate=" + regdate + ", file=" + file + ", blist=" + "]";
	}
	
	
	
}
