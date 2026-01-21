package __SpringBoot2.__star_Spring_io.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import __SpringBoot2.__star_Spring_io.services.AnimeServices;

@ExtendWith(SpringExtension.class)
class AnimeComtrollerTest {

	@InjectMocks
	private AnimeComtroller animeComtroller;
	@Mock
	private AnimeServices animeServices;
	
	
	
	
	
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
