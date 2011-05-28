// Copyright Keith D Gregory
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package net.sf.kdgcommons.buffer;

import java.nio.ByteBuffer;


/**
 *  This interface defines a methods for absolute access to a byte-oriented
 *  buffer. Its purpose is to allow code to use either a <code>ByteBuffer</code>
 *  (typically for testing) or a <code>MappedFileBuffer</code>.
 *  <p>
 *  All methods use a <code>long</code> index. However, depending on the actual
 *  implementation, index values may be limited to <code>Integer.MAX_VALUE</code>.
 */
public interface BufferFacade
{
    /**
     *  Returns the single byte at the specified index (relative to the
     *  relocation base).
     */
    public byte get(long index);


    /**
     *  Updates the single byte at the specified index (relative to the
     *  relocation base).
     */
    public void put(long index, byte value);


    /**
     *  Returns the 2-byte <code>short</code> value at the specified index
     *  (relative to the relocation base).
     */
    public short getShort(long index);


    /**
     *  Sets the 2-byte <code>short</code> value at the specified index
     *  (relative to the relocation base).
     */
    public void putShort(long index, short value);


    /**
     *  Returns the 4-byte <code>int</code> value at the specified index
     *  (relative to the relocation base).
     */
    public int getInt(long index);


    /**
     *  Sets the 4-byte <code>int</code> value at the specified index
     *  (relative to the relocation base).
     */
    public void putInt(long index, int value);


    /**
     *  Returns the 8-byte <code>long</code> value at the specified index
     *  (relative to the relocation base).
     */
    public long getLong(long index);


    /**
     *  Sets the 8-byte <code>long</code> value at the specified index
     *  (relative to the relocation base).
     */
    public void putLong(long index, long value);


    /**
     *  Returns the 4-byte <code>float</code> value at the specified index
     *  (relative to the relocation base).
     */
    public float getFloat(long index);


    /**
     *  Sets the 4-byte <code>float</code> value at the specified index
     *  (relative to the relocation base).
     */
    public void putFloat(long index, float value);


    /**
     *  Returns the 8-byte <code>double</code> value at the specified index
     *  (relative to the relocation base).
     */
    public double getDouble(long index);


    /**
     *  Sets the 8-byte <code>double</code> value at the specified index
     *  (relative to the relocation base).
     */
    public void putDouble(long index, double value);


    /**
     *  Returns the 2-byte <code>char</code> value at the specified index
     *  (relative to the relocation base).
     */
    public char getChar(long index);


    /**
     *  Sets the 2-byte <code>char</code> value at the specified index
     *  (relative to the relocation base).
     */
    public void putChar(long index, char value);


    /**
     *  Returns an array containing the <code>len</code> bytes starting
     *  at the specified index (relative to the relocation base).
     */
    public byte[] getBytes(long index, int len);


    /**
     *  Inserts the specified array into the buffer, starting at the given
     *  index (relative to the relocation base).
     */
    public void putBytes(long index, byte[] value);


    /**
     *  Returns a <code>ByteBuffer</code> that represents a slice of the
     *  underlying buffer (ie, shares the same backing store), starting at
     *  the given index (relative to the relocation base) and extending to
     *  the end of the underlying buffer.
     *  <p>
     *  The semantics of this method depend on the underlying buffer. For a
     *  normal <code>ByteBuffer</code>, the limit will be determined by the
     *  size of the original buffer. For a <code>MappedFileBuffer</code>,
     *  the limit will depend on the particular segment containing the offset.
     */
    public ByteBuffer slice(long index);


    /**
     *  Returns the capacity of the wrapped buffer.
     */
    public long capacity();
}
