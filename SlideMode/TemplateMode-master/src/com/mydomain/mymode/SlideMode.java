package com.mydomain.mymode;

import java.lang.reflect.Field;
import java.net.*;
import java.io.*;
import java.util.*;

import org.omg.CORBA.PUBLIC_MEMBER;

import processing.mode.java.*;
import processing.mode.java.runner.*;
import processing.app.*;

import com.illposed.osc.*;

import java.awt.*;
import java.awt.datatransfer.*;

/**
 * Mode Template for extending Java mode in Processing IDE 2.0 or later.
 *
 */
public class SlideMode extends JavaMode {
	Editor editor;

	public SlideMode(Base base, File folder) {
		super(base, folder);

		for (Mode m : base.getModeList()) {
			if (m.getClass() == JavaMode.class) {
				JavaMode jMode = (JavaMode) m;
				librariesFolder = jMode.getLibrariesFolder();
				rebuildLibraryList();
				break;
			}
		}

		// Fetch examples and reference from java mode
		examplesFolder = Base.getContentFile("modes/java/examples");
		referenceFolder = Base.getContentFile("modes/java/reference");
	}

	@Override
	public String getTitle() {
		return "Slide";
	}

	/**
	 * Create a new editor associated with this mode.
	 */

	@Override
	public Editor createEditor(Base base, String path, EditorState state) {
		// editor = new SlideEditor(base, path, state, this);
		// editor =new JavaEditor(base, path, state, this);
		// mt.setEditor(this);
		editor = new SlideEditor(base, path, state, this);
		return editor;
	}
	public String modePath(String str) {
		return folder.getAbsolutePath().replace("\\", "/") + "/mode/" + str;
	}


	public String readTXT(String path) {
		// String str="";
		StringBuilder str = new StringBuilder();
		BufferedReader br = null;
		try {
			File file = new File(path);
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				// str += line + "\n";
				str.append(line);
				str.append('\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// ストリームは必ず finally で close します。
				br.close();
			} catch (IOException e) {
			}
		}
		// return str;
		return new String(str);
	}

}
