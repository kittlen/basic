package impl;

import java.util.ArrayList;
import java.util.List;
import basedao.Hibernatedao;
import basedao.iHibernatedao;
import model.Notepad;
import service.NotepadInterface;
import util.util;

public class NotepadImpl implements NotepadInterface {
	iHibernatedao hb = new Hibernatedao();

	@Override
	public boolean insert(Notepad notepad) {
		// TODO Auto-generated method stub
		String retStr = hb.save(notepad).toString();
		if ("0".equals(retStr)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Notepad notepad) {
		// TODO Auto-generated method stub
		Notepad not=(Notepad) hb.getObj(Notepad.class, notepad.getId());
		not.setTitle(notepad.getTitle());
		not.setContent(notepad.getContent());
		not.setDate(notepad.getDate());
		not.setType(notepad.getType());
		if(hb.update(not)==1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Notepad notepad) {
		// TODO Auto-generated method stub
		if(hb.delete(notepad)==1) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notepad> select() {
		// TODO Auto-generated method stub
		List<Notepad> list=new ArrayList<Notepad>();
		String HQL="from Notepad note ORDER BY note.date DESC";
		list=hb.select(HQL);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notepad> select(String where) {
		// TODO Auto-generated method stub
		List<Notepad> list=new ArrayList<Notepad>();
		String HQL="from Notepad note where note.Content like %?% or note.Title like %?%";
		Object[] parmas={where,where};
		list=hb.select(HQL, parmas);
		return list;
	}

}
