package edu.kh.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {

	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberNickname;
	private String enrollDate;
	private String memberDelFlag;
		
	
}
