package service;

import impl.NotepadImpl;

public class NotepadFactory {
	public static NotepadInterface notepadInterFace(){
		return new NotepadImpl();
	}

}
