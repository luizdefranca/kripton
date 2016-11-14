package kripton70.core;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;

public abstract class AbstractJacksonBinder extends AbstractBinder implements Binder2Jackson {
	public abstract JsonFactory createInnerFactory();
	
	public AbstractJacksonBinder() {
		innerFactory = createInnerFactory();
	}

	public JsonFactory innerFactory;

	public BinderSerializer createSerializer(DataOutput out) throws IOException {
		return createSerializer(out, JsonEncoding.UTF8);
	}

	public BinderSerializer createSerializer(OutputStream out) throws IOException {
		return createGenerator(out, JsonEncoding.UTF8);
	}

	public BinderSerializer createSerializer(Writer writer) throws IOException {
		return new BinderSerializer(this, innerFactory.createGenerator(writer), getSupportedFormat());
	}

	public BinderSerializer createSerializer(DataOutput out, JsonEncoding encoding) throws IOException {
		return new BinderSerializer(this, innerFactory.createGenerator(out, encoding), getSupportedFormat());
	}

	public BinderSerializer createGenerator(File file, JsonEncoding encoding) throws IOException {
		return new BinderSerializer(this, innerFactory.createGenerator(file, encoding), getSupportedFormat());
	}

	public BinderSerializer createSerializer(File file) throws IOException {
		return createGenerator(file, JsonEncoding.UTF8);
	}

	public BinderSerializer createSerializer(OutputStream out, JsonEncoding encoding) throws IOException {
		return new BinderSerializer(this, innerFactory.createGenerator(out, encoding), getSupportedFormat());
	}

	public BinderParser createParser(byte[] data) throws IOException {
		return new BinderParser(this, innerFactory.createParser(data), getSupportedFormat());
	}

	public BinderParser createParser(char[] content) throws IOException {
		return new BinderParser(this, innerFactory.createParser(content), getSupportedFormat());
	}

	public BinderParser createParser(DataInput in) throws IOException {
		return new BinderParser(this, innerFactory.createParser(in), getSupportedFormat());
	}

	public BinderParser createParser(File file) throws IOException {
		return new BinderParser(this, innerFactory.createParser(file), getSupportedFormat());
	}

	public BinderParser createParser(InputStream in) throws IOException {
		return new BinderParser(this, innerFactory.createParser(in), getSupportedFormat());
	}

	public BinderParser createParser(Reader reader) throws IOException {
		return new BinderParser(this, innerFactory.createParser(reader), getSupportedFormat());
	}

	public BinderParser createParser(String content) throws IOException {
		return new BinderParser(this, innerFactory.createParser(content), getSupportedFormat());
	}

	public BinderParser createParser(URL url) throws IOException {
		return new BinderParser(this, innerFactory.createParser(url), getSupportedFormat());
	}

}