package com.mbc.mapper;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mbc.domain.Criteria;
import com.mbc.domain.ReviewVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReviewMapperTests {
	
	
	private Long[] pnoArr = {1L, 2L, 3L, 4L, 5L};
	
	
	@Autowired
	private ReviewMapper mapper;
	
	
	@Test
	public void testCreate() {
		
		IntStream.rangeClosed(1, 5)
			.forEach(i-> {
				ReviewVO vo = ReviewVO.builder()
						.pno(pnoArr[i%5])
						.uno(pnoArr[i%5])
						.r_content("리뷰 테스트" + i)
						.r_img_path("")
						.r_img_name("")
						.r_img_o_name("")
						.build();
						
						
				
				mapper.insert(vo);
				
			});
	}
	
	
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	
	@Test
	public void testRead() {
		Long targetRno = 2L;
		 ReviewVO vo = mapper.read(targetRno);
		 
		 log.info(vo);
	}
	
	@Test
	public void testDelete() {
		Long targetRno = 10L;
		
		mapper.delete(targetRno);
	}
	
	
	@Test
	public void testUpdate() {
		ReviewVO vo = mapper.read(9L);
		
		vo.setR_content("수정된 내용");
		
		mapper.update(vo);
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		
		mapper.getListWithPaging(cri, 2L)
		.forEach(uno-> log.info(uno));
		
	}
}
