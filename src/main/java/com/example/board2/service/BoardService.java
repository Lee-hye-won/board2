package com.example.board2.service;

import java.util.List;

import com.example.board2.dto.Board;

public interface BoardService {
	public int maxNum() throws Exception;

	// boardMapper의 insertData의 파라미터 타입 참고
	public void insertData(Board board) throws Exception;

	public int getDataCount(String searchKey, String searchValue) throws Exception;

	public List<Board> getLists(String searchKey, String searchValue, int start, int end) throws Exception;

	public void updateHitCount(int num) throws Exception;

	public Board getReadData(int num) throws Exception;

	public void updateData(Board board) throws Exception;

	public void deleteData(int num) throws Exception;
}
