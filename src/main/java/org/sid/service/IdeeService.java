package org.sid.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.sid.InterfaceDao.IdeeMotsCleRepository;
import org.sid.InterfaceDao.IdeeRepository;
import org.sid.InterfaceDao.MotsCleeRepository;
import org.sid.entities.Genre;
import org.sid.entities.Idee;
import org.sid.entities.IdeeMotsCle;
import org.sid.entities.MotsCle;
import org.sid.entities.TypeIdee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ch.qos.logback.core.net.SyslogOutputStream;
import rita.RiMarkov;

@CrossOrigin("*")
@RestController
public class IdeeService {
	@Autowired
	private IdeeRepository ideeRepository;
	@Autowired
	private MotsCleeRepository motcleeRepository;
	@Autowired
	private IdeeMotsCleRepository  ideeMotsCleRepository;
	
	IdeeMotsCle ideeMotsCle =new IdeeMotsCle();
	//List<MotsCle> listMc;
	 RiMarkov rm = new RiMarkov(3);
	StringBuilder sb = new StringBuilder();
	

	@RequestMapping(value="/idees", method= RequestMethod.POST)
	public Idee SaveIdee(@Valid @RequestBody Idee i ){
		return ideeRepository.save(i);
	}
	
	@RequestMapping(value="/idees", method=RequestMethod.GET)
	public List<Idee> getIdees(){
		return ideeRepository.findAll();
	}
	
	@RequestMapping(value="/idees/{id}", method=RequestMethod.GET)
	public Idee getIdee(@PathVariable Long id){
		return ideeRepository.findById(id).orElse(null);
	}
	
	@RequestMapping(value="/idees/{id}", method=RequestMethod.PUT)
	public Idee updateIdee(@RequestBody Idee i){
		return ideeRepository.save(i);
	}
	//permet de generer des nouvelles idées à partir des idées existants

	@RequestMapping(value="/idees/ideesByidee", method=RequestMethod.GET)
	public String[] generateIdee(){
		
		for (String s :ideeRepository.getIdeeByidee())
		{
//		    sb.append(s);
//		    sb.append("\n");
			 rm.loadText(s+" ");
		}	     
		return  rm.generateSentences(3);
	}
	// permet de generer des idés de meme genre
	@RequestMapping(value="/idees/ideesByGenre", method=RequestMethod.POST)
	public String[] generateIdeeByGenre(@RequestBody Idee i){
		System.out.println(i.getGenre().getGenre()+" test tes test test   "+i.getTypeIdee().getTypeIdee());
		for (String s :ideeRepository.getIdeeByGenre(i.getGenre().getIdGenre(),i.getTypeIdee().getIdTypeIdee()))
		{	
			System.out.println(s);
		   // sb.append(s);
		   // sb.append("\n");
		    rm.loadText(s+" ");
		}	     
	   	//  rm.loadText(sb.toString());
	 
		return  rm.generateSentences(3);
		
	}
	
	
	@RequestMapping(value="/motscle", method= RequestMethod.POST)
	public MotsCle SaveMotcle(@Valid @RequestBody MotsCle i ){
		return motcleeRepository.save(i);
	}
	@CrossOrigin("*")
	@RequestMapping(value="/motscles", method= RequestMethod.POST)
	public  void SaveListMotcle(@Valid @RequestBody List< MotsCle> motCles ){
		for(MotsCle m:motCles){
			System.out.println("SaveListMotcle "+m.getmCle());
			SaveMotcle(m);
		}
		
	}
	
	@RequestMapping(value="/motscle", method=RequestMethod.GET)
	public List<MotsCle> getMotscles(){
		return motcleeRepository.findAll();
	}
	
	@RequestMapping(value="/motscle/{id}", method=RequestMethod.GET)
	public MotsCle getMotcle(@PathVariable Long id){
		return motcleeRepository.findById(id).orElse(null);
		
	}
	
	@RequestMapping(value="/ideeMotsCle/{id}", method=RequestMethod.GET)
	public Optional<IdeeMotsCle> getIdeeMotcle(@PathVariable Long id){
		return ideeMotsCleRepository.findById(id);
		
	}

	@RequestMapping(value="/ideeMotsCle", method= RequestMethod.POST)
	public IdeeMotsCle SaveIdeeMotcle(@Valid @RequestBody IdeeMotsCle i ){
		return ideeMotsCleRepository.save(i);
	}
	//@CrossOrigin("*")
	/*@RequestMapping(value="/saveIdeeANDMotcle/{idee}", method= RequestMethod.POST)
	public void SaveIdeeANDMotcle(@PathVariable Idee idee,@Valid @RequestBody  List<MotsCle>listMc ){
		System.out.println("ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");
		
		SaveIdee(idee);
		ideeMotsCle.setIdee(idee);
		for(MotsCle m:listMc){
			SaveMotcle(m);
			ideeMotsCle.setMotsCle(m);
			SaveIdeeMotcle(ideeMotsCle);
			System.out.println(m.getmCle());
		}
		
		System.out.println("fin!!!!");
	}
	*/
}
