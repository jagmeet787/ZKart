package zkart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import zkart.entity.Color;
import zkart.service.ColorService;

@CrossOrigin(origins = "*" )
@RestController
@RequestMapping("/color")
public class ColorController {
	
	@Autowired
	private ColorService colorService;
	
	@RequestMapping(method=RequestMethod.POST , value="/addColor")
	public ResponseEntity<String> addColor(@RequestBody Color color)
	{
		if(colorService.updateColor(color))
		return new ResponseEntity<>("Success.", HttpStatus.OK);
		
		return new ResponseEntity<>("Nothing Updated!", HttpStatus.BAD_REQUEST);
		
		
	}
	
	@RequestMapping("")
	public List<Color> getAllColor()
	{
		List<Color> color=colorService.getColor();
		return color;
	}
	
}
