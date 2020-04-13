package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;

import model.Notepad;
import net.sf.json.JSONArray;
import service.NotepadFactory;
import service.NotepadInterface;
import util.util;

public class NotepadAction implements ModelDriven<Notepad>{
	private Notepad notepad=new Notepad();
	NotepadInterface iNotepad=NotepadFactory.notepadInterFace();
	private String where;

	@Override
	public Notepad getModel() {
		// TODO Auto-generated method stub
		return notepad;
	}
	
	
	public void insert() {
		if(iNotepad.insert(notepad)) {
			pwOut("success");
		}
		pwOut("error");
	}
	public void update() {
		if(iNotepad.update(notepad)) {
			pwOut("success");
		}
		pwOut("error");
	}
	public void delete() {
		if(iNotepad.delete(notepad)) {
			pwOut("success");
		}
		pwOut("error");
	}
	public void select() {
		List<Notepad> list=iNotepad.select();
		if(list!=null) {
			pwOut(JSONArray.fromObject(list).toString());
			}else {
				pwOut("error");
			}
	}
	public void selectWhere() {
		List<Notepad> list=iNotepad.select(where);
		if(list!=null) {
		pwOut(JSONArray.fromObject(list).toString());
		}else {
			pwOut("error");
		}
	}
	public static void pwOut(String outStr){
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.write(outStr);
		pw.flush();
		pw.close();
		
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

}
