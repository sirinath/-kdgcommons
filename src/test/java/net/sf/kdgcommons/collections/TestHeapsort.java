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

package net.sf.kdgcommons.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import junit.framework.TestCase;


public class TestHeapsort
extends TestCase
{
//----------------------------------------------------------------------------
//  Support Code
//----------------------------------------------------------------------------

    // the "big" tests will all start with a random int[]
    private static int[] createRandomArray(int size)
    {
        int[] arr = new int[size];
        for (int ii = 0 ; ii < arr.length ; ii++)
            arr[ii] = (int)(Math.random() * Integer.MAX_VALUE);
        return arr;
    }


    // and will need to compare to an already-sorted array
    private static int[] createSortedCopy(int[] src)
    {
        int[] ret = new int[src.length];
        System.arraycopy(src, 0, ret, 0, src.length);
        Arrays.sort(ret);
        return ret;
    }


    // and we'll convert these to objects if needed
    private static Integer[] toObjectArray(int[] src)
    {
        Integer[] ret = new Integer[src.length];
        for (int ii = 0 ; ii < src.length ; ii++)
            ret[ii] = Integer.valueOf(src[ii]);
        return ret;
    }


    // we'll throw a twist into the ordering for the int[] tess
    public static class ReversingIntComparator
    implements Heapsort.IntComparator
    {
        public int compare(int i1, int i2)
        {
            return (i1 > i2) ? -1
                 : (i1 < i2) ? 1
                 : 0;
        }
    }


    // this will be used to verify the O(NlogN) property; it orders by
    // increasing values so we can compare result to Arrays.sort()
    public static class CountingIntComparator
    implements Heapsort.IntComparator
    {
        public int count;

        public int compare(int i1, int i2)
        {
            return (i1 < i2) ? -1
                 : (i1 > i2) ? 1
                 : 0;
        }
    }


    // but use a straightforward comparison for integers
    public static class ForwardComparator<T extends Comparable<T>>
    implements Comparator<T>
    {
        public int compare(T o1, T o2)
        {
            return o1.compareTo(o2);
        }
    }


    private void assertEquals(int[] expected, int[] actual)
    {
        // we'll convert to List<Integer> because the reporting is nicer

        ArrayList<Integer> expected2 = new ArrayList<Integer>(expected.length);
        for (int ii = 0 ; ii < expected.length ; ii++)
            expected2.add(Integer.valueOf(expected[ii]));

        ArrayList<Integer> actual2 = new ArrayList<Integer>(expected.length);
        for (int ii = 0 ; ii < expected.length ; ii++)
            actual2.add(Integer.valueOf(actual[ii]));

        assertEquals(expected2, actual2);
    }


//----------------------------------------------------------------------------
//  Test Cases
//----------------------------------------------------------------------------

    public void testIntSortEmptyArray() throws Exception
    {
        int[] src = new int[0];
        int[] exp = new int[0];

        Heapsort.sort(src, new ReversingIntComparator());
        assertEquals(exp, src);
    }


    public void testIntSortOneElement() throws Exception
    {
        int[] src = new int[] { 3 };
        int[] exp = new int[] { 3 };

        Heapsort.sort(src, new ReversingIntComparator());
        assertEquals(exp, src);
    }


    public void testIntSortTwoElements() throws Exception
    {
        int[] src = new int[] { 3, 5 };
        int[] exp = new int[] { 5, 3 };

        Heapsort.sort(src, new ReversingIntComparator());
        assertEquals(exp, src);
    }


    public void testIntSortThreeElements() throws Exception
    {
        int[] src = new int[] { 5, 3, 4 };
        int[] exp = new int[] { 5, 4, 3 };

        Heapsort.sort(src, new ReversingIntComparator());
        assertEquals(exp, src);
    }


    public void testIntSortFourElements() throws Exception
    {
        int[] src = new int[] { 5, 3, 4, 12 };
        int[] exp = new int[] { 12, 5, 4, 3 };

        Heapsort.sort(src, new ReversingIntComparator());
        assertEquals(exp, src);
    }


    public void testIntSortManyElements() throws Exception
    {
        final int size = 10000;
        final int log2Size = 14;

        int[] src = createRandomArray(size);
        int[] exp = createSortedCopy(src);

        CountingIntComparator cmp = new CountingIntComparator();
        Heapsort.sort(src, cmp);
        assertEquals(exp, src);

        // we should have at most 3 comparisons per swap, so assert those
        int expectedCompares = 3 * size * log2Size;
        assertTrue("comparison count (was " + cmp.count + ", expected " + expectedCompares + ")",
                   cmp.count < expectedCompares);
    }


    public void testObjectSort() throws Exception
    {
        int[] base = createRandomArray(1000);
        Integer[] src = toObjectArray(base);
        Integer[] exp = toObjectArray(createSortedCopy(base));

        Heapsort.sort(src);
        assertEquals(Arrays.asList(exp), Arrays.asList(src));
    }


    public void testListSort() throws Exception
    {
        List<Integer> base = Arrays.asList(toObjectArray(createRandomArray(1000)));
        List<Integer> src = new ArrayList<Integer>(base);
        List<Integer> exp = new ArrayList<Integer>(base);
        Collections.sort(exp);

        Heapsort.sort(src);
        assertEquals(exp, src);
    }

}