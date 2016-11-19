package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import Entities.Programme;
import Entities.Staff;
import Entities.Student;
import presentation.MessageBox;

public class SaveFileManager {

    final String cryptoKey = "2w3xe4Cr5vt6bydc";
	private String filename;
	private Store store;
	private Gson gson;
	private FileWriter fw;
	
	final boolean encryptionModeEnabled = false;

	public SaveFileManager() {
			filename = "src/store77";
		}

	public SaveFileManager(String filename) {
		this.filename = filename;
	}
	
	public Store Load() {
		gson = new Gson();
		try {
			if(encryptionModeEnabled)
			store = gson.fromJson(Crypto.decrypt(readAllText(filename), cryptoKey) , Store.class);
			else
				store = gson.fromJson(readAllText(filename), Store.class);
			if(store == null)
				createNewFile();
		} catch (JsonSyntaxException e) {
			MessageBox.Error("Could not parse data from "+filename+"\n\t-File Currupted(may have been manually modified incorrectly)\nDetails:\n\t\t"+e.getMessage());
			int n = JOptionPane.showConfirmDialog(
				    null,
				    "Create And use new file?",
				    "Data File was Currupted",
				    JOptionPane.YES_NO_OPTION);
			if(n == 0)
				if(createNewFile()){
				MessageBox.Show("File Created and Saved Sucessfully");
				return store;
				}
				else
					MessageBox.Show("Could not create file database.");
			
				MessageBox.Show("Exiting System");
				System.exit(0);
		}
		return this.store;
	}
	
	private boolean createNewFile(){
		store = new Store(); 
		return SaveChanges();
	}

	public static String readAllText(String path) {
		Scanner reader = null;
		String content ="";
		try {
			reader = new Scanner(new File(path));
			content = reader.useDelimiter("\\ZZ0#jklk@").next();
		} catch (FileNotFoundException e) {
		return null;
		}
		if(reader != null)
		reader.close();
		return content;
	}
	public Store getStore(){
		return this.store;
	}
	public boolean SaveChanges() {
		try {
			fw = new FileWriter(filename);
			if(encryptionModeEnabled)
			fw.write(Crypto.encrypt(gson.toJson(store),cryptoKey));
			else
			fw.write(gson.toJson(store));
			fw.close();
			return true;
		} catch (IOException e) {
			MessageBox.Error(e.getMessage());
			return false;
		}
	}
	public void addStaff(Staff staff){
		
		store.getStaffs().add(staff);
		SaveChanges();
		
	}
	public void addStudent(Student student){
		
		store.getStudents().add(student);
		SaveChanges();
		
	}

	public void addProgramme(Programme programme){
		
		store.getProgrammes().add(programme);
		SaveChanges();
		
	}

}
