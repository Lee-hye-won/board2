package com.example.board2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.board2.dto.Board;

@Mapper
public interface BoardDao {
	public int maxNum() throws Exception;
	
	//boardMapper의 insertData의 파라미터 타입 참고
	public void insertData(Board board) throws Exception;
	
	public int getDataCount(String searchKey, String searchValue) throws Exception;
	
	public List<Board> getLists(String searchKey, String searchValue, int start, int end) throws Exception;
	
}
