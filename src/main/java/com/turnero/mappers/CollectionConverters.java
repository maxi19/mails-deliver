package com.turnero.mappers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class CollectionConverters<K, L> extends AbstractConverter <List<K>, List<L>>  {

	@Autowired
	private ModelMapper modelMapper;


    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }

    private Class<L> j;

    private List<L> outCLass ;


	@Override
	protected List<L> convert(List<K> list) {
		List<L> outList = createInstanceList();
				list.stream().forEach(K ->{
					Class<L> l =  (Class<L>) createInstance();
					modelMapper.map(K,l) ;
					outList.add((L) l);
				});
		return outList;
	}

	public List<L> createInstanceList(){

		return this.outCLass = new ArrayList<>();

	}


	public L createInstance() {
	    try {
	        return j.getDeclaredConstructor().newInstance();
	    } catch (Exception e) {
	        throw new RuntimeException("Error while creating an instance.");
	    }
	}


}
