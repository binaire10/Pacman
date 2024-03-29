package fr.univ_amu.game.util;

import fr.univ_amu.game.event.Event;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class Utility {
    public static <T> Stream<T> iteratorToStream(Iterator<T> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 0), false);
    }

    public static <T> Stream<T> iteratorToStream(Iterator<T> iterator, long size) {
        return StreamSupport.stream(Spliterators.spliterator(iterator, size, 0), false);
    }

    public static ByteBuffer readFile(URL file) throws URISyntaxException, IOException {
        var path = Path.of(file.toURI());
        try {
            return FileChannel.open(path).map(FileChannel.MapMode.READ_ONLY, 0, Files.size(path));
        } catch (UnsupportedOperationException | IOException e) {
            return ByteBuffer.wrap(Files.readAllBytes(path));
        }
    }

    // https://stackoverflow.com/questions/4332264/wrapping-a-bytebuffer-with-an-inputstream
    public static InputStream asInputStream(ByteBuffer buffer) {
        if (buffer.hasArray()) {
            // use heap buffer; no array is created; only the reference is used
            return new ByteArrayInputStream(buffer.array());
        }
        return new ByteBufferBackedInputStream(buffer);
    }

    public static <T extends Event> void dispatch(Event event, Class<T> value, Consumer<T> handle) {
        if (value.isInstance(event))
            handle.accept(value.cast(event));
    }
}
