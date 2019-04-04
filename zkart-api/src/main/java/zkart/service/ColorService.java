package zkart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zkart.entity.Color;
import zkart.repository.ColorRepository;

@Service
public class ColorService {

	@Autowired
	private ColorRepository colorRepository;
	
	public List<Color> getColor()
	{
		ArrayList<Color> list = new ArrayList<Color>();
		colorRepository.findAll().forEach(list::add);
		return list;
	}

	public boolean updateColor(Color color)
	{
		return colorRepository.save(color)!=null;
	}
}
