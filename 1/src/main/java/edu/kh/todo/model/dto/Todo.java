package edu.kh.todo.model.dto;

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
public class Todo {

	private int todoNo;
	private String todoTitle;
	private String todoMemo;
	private String todoDate;
	private String todoDelFlag;
	private int memberNo;
}
