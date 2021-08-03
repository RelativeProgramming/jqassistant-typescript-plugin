package org.jqassistant.contrib.plugin.ecmascript.impl.graaljs;

import org.graalvm.polyglot.io.FileSystem;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.util.Map;
import java.util.Set;

/**
 * {@link FileSystem} implementation to resolve "classpath://" URIs as classpath resource.
 */
public final class ClasspathFilesystem implements FileSystem {

    private final FileSystem delegate = FileSystem.newDefaultFileSystem();

    @Override
    public Path parsePath(URI uri) {
        if (uri.getScheme()
            .equals("classpath")) {
            String resource = uri.getSchemeSpecificPart();
            URL systemResource = ClassLoader.getSystemResource(resource);
            if (systemResource == null) {
                throw new UnsupportedOperationException("Cannot local classpath resource: " + resource);
            }
            try {
                return Paths.get(systemResource.toURI());
            } catch (URISyntaxException e) {
                throw new UnsupportedOperationException("Cannot resolve URI from system resource: " + systemResource,
                    e);
            }
        }
        return delegate.parsePath(uri);
    }

    // delegate methods

    @Override
    public Path parsePath(String path) {
        return delegate.parsePath(path);
    }

    @Override
    public void checkAccess(Path path, Set<? extends AccessMode> modes, LinkOption... linkOptions) throws IOException {
        delegate.checkAccess(path, modes, linkOptions);
    }

    @Override
    public void createDirectory(Path dir, FileAttribute<?>... attrs) throws IOException {
        delegate.createDirectory(dir, attrs);
    }

    @Override
    public void delete(Path path) throws IOException {
        delegate.delete(path);
    }

    @Override
    public SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs)
        throws IOException {
        return delegate.newByteChannel(path, options, attrs);
    }

    @Override
    public DirectoryStream<Path> newDirectoryStream(Path dir, DirectoryStream.Filter<? super Path> filter)
        throws IOException {
        return delegate.newDirectoryStream(dir, filter);
    }

    @Override
    public Path toAbsolutePath(Path path) {
        return delegate.toAbsolutePath(path);
    }

    @Override
    public Path toRealPath(Path path, LinkOption... linkOptions) throws IOException {
        return delegate.toRealPath(path, linkOptions);
    }

    @Override
    public Map<String, Object> readAttributes(Path path, String attributes, LinkOption... options) throws IOException {
        return delegate.readAttributes(path, attributes, options);
    }

    @Override
    public void setAttribute(Path path, String attribute, Object value, LinkOption... options) throws IOException {
        delegate.setAttribute(path, attribute, value, options);
    }

    @Override
    public void copy(Path source, Path target, CopyOption... options) throws IOException {
        delegate.copy(source, target, options);
    }

    @Override
    public void move(Path source, Path target, CopyOption... options) throws IOException {
        delegate.move(source, target, options);
    }

    @Override
    public void createLink(Path link, Path existing) throws IOException {
        delegate.createLink(link, existing);
    }

    @Override
    public void createSymbolicLink(Path link, Path target, FileAttribute<?>... attrs) throws IOException {
        delegate.createSymbolicLink(link, target, attrs);
    }

    @Override
    public Path readSymbolicLink(Path link) throws IOException {
        return delegate.readSymbolicLink(link);
    }

    @Override
    public void setCurrentWorkingDirectory(Path currentWorkingDirectory) {
        delegate.setCurrentWorkingDirectory(currentWorkingDirectory);
    }

    @Override
    public String getSeparator() {
        return delegate.getSeparator();
    }

    @Override
    public String getPathSeparator() {
        return delegate.getPathSeparator();
    }

    @Override
    public String getMimeType(Path path) {
        return delegate.getMimeType(path);
    }

    @Override
    public Charset getEncoding(Path path) {
        return delegate.getEncoding(path);
    }

    @Override
    public Path getTempDirectory() {
        return delegate.getTempDirectory();
    }

    @Override
    public boolean isSameFile(Path path1, Path path2, LinkOption... options) throws IOException {
        return delegate.isSameFile(path1, path2, options);
    }

}
