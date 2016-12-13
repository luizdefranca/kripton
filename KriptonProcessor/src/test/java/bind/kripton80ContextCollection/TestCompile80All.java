package bind.kripton80ContextCollection;

import java.io.IOException;

import org.junit.Test;

import bind.AbstractBindTypeProcessorTest;
import bind.kripton80ContextCollection.Bean80;
import bind.kripton80ContextCollection.BeanEnum;

public class TestCompile80All extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Bean80.class, BeanEnum.class);
	}

}
