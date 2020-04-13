package service;

import java.util.List;

import model.Notepad;

public interface NotepadInterface {
	boolean insert(Notepad notepad);
	boolean update(Notepad notepad);
	boolean delete(Notepad notepad);
	List<Notepad> select();
	List<Notepad> select(String where);

}
