package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.impl

import cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.remote.datasource.mapper.impl.GraphUtils.findPathInGraph
import org.junit.Assert.assertEquals
import org.junit.Test

class GraphUtilsTest {

    @Test
    fun `get more concise way to find path in graph`() {
        val graph = GraphUtils.Graph(
            listOf(
                "CAD" to "AUD",
                "EUR" to "CAD",
                "CAD" to "USD",
                "USD" to "CAD",
                "EUR" to "AUD",
                "AUD" to "EUR"
            )
        )

        assertEquals(
            listOf(
                "USD" to "CAD",
                "CAD" to "AUD",
                "AUD" to "EUR"
            ),
            findPathInGraph(graph, "USD", "EUR")
        )

    }

    @Test
    fun `in large full conected graph, get path between two distant nodes`() {
        val graph = GraphUtils.Graph(
            listOf(
                "A" to "M",
                "G" to "I",
                "F" to "G",
                "I" to "P",
                "A" to "B",
                "A" to "Q",
                "B" to "C",
                "B" to "F",
                "C" to "R",
                "C" to "J",
                "J" to "L",
                "J" to "K",
                "J" to "O",
                "F" to "H",
                "H" to "R",
                "I" to "O",
                "O" to "X"
            )
        )

        assertEquals(
            listOf(
                "A" to "B",
                "B" to "C",
                "C" to "J",
                "J" to "O",
                "O" to "X"
            ),
            findPathInGraph(graph, "A", "X")
        )

        assertEquals(
            listOf(
                "A" to "B",
                "B" to "C",
                "C" to "J",
                "J" to "L"
            ),
            findPathInGraph(graph, "A", "L")
        )
    }

    @Test
    fun `in large NON conected graph, check that no path exist between two nodes`() {
        val graph = GraphUtils.Graph(
            listOf(
                "Z" to "A",
                "A" to "M",
                "G" to "I",
                "F" to "G",
                "I" to "P",
                "A" to "B",
                "A" to "Q",
                "B" to "C",
                "B" to "F",
                "C" to "R",
                "C" to "J",
                "J" to "L",
                "J" to "K",
                "J" to "O",
                "F" to "H",
                "H" to "R",
                "I" to "O",
                "O" to "X"
            )
        )

        assertEquals(
            null,
            findPathInGraph(graph, "X", "Z")
        )
    }

    @Test
    fun `in large conected with multiple relations`() {
        val graph = GraphUtils.Graph(
            listOf(
                "A" to "F",
                "A" to "I",
                "A" to "B",
                "A" to "H",
                "A" to "C",
                "F" to "B",
                "B" to "E",
                "H" to "K",
                "H" to "A",
                "C" to "D",
                "J" to "A",
                "G" to "B",
                "D" to "B",
                "D" to "K",
                "K" to "B",
                "K" to "D",
                "E" to "D",
                "E" to "K",
                "E" to "J"
            )
        )

        assertEquals(
            listOf(
                "A" to "F",
                "F" to "B",
                "B" to "E",
                "E" to "J"
            ),
            findPathInGraph(
                graph, "A", "J"
            )
        )
        assertEquals(
            listOf(
                "K" to "B",
                "B" to "E",
                "E" to "J"
            ),
            findPathInGraph(graph, "K", "J")
        )
    }
}