package edu.sjsu.cs151.taskboard;
import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import javafx.scene.paint.Color;

public class ColorAdapter extends XmlAdapter<String, Color> {
    public Color unmarshal(String v) throws Exception {
        return Color.valueOf(v);
    }

	@Override
	public String marshal(Color v) throws Exception {
		return v.toString();
	}
}