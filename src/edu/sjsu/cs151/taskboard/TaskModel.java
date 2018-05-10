package edu.sjsu.cs151.taskboard;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class TaskModel {
    private String name = "Task1";
    private String description;
    
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate dueDate;
    
    private ArrayList<String> tags;
    
    private String status = "NONE";

    public TaskModel(String name, String description, LocalDate dueDate, ArrayList<String> tags) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.tags = tags;
    }

    public TaskModel(String name) 
    { 
    	this.name = name; 
    }
    
    public TaskModel() {
		// TODO Auto-generated constructor stub
	}
    
    public void setTags(ArrayList<String> tags) 
    {
		this.tags = tags; 
	}
		
    public void setName(String name) 
    { 
    	this.name = name; 
    }
    
    public String getName() 
    { 
    	return name;
    }


    public void setDescription(String d) 
    { 
    	description = d; 
    }

    public String getDescription() 
    { 
    	return description; 
    }

    public void setDueDate(LocalDate date) 
    { 
    	dueDate = date; 
    }

    public LocalDate getDueDate() 
    {
        return dueDate;
    }

    public void addTag(String tag) 
    { 
    	tags.add(tag); 
    }

    // TODO: 5/3/18 Determine how we will allow a user to remove a tag.
    // The best thing to do would be to allow them to click an "x" on the tag
    // but we may not want to choose to implement that (depends on time).
    public void removeTag(String tag) 
    {
        tags.remove(tag);
    }
    
    public ArrayList<String> getTags() 
    {
		return tags;
	}
    
    public void setStatus(String status)
    {
    	this.status = status;
    }
    
    public String getStatus()
    {
    	return status;
    }
}
