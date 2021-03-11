package cat.devsofthecoast.bemobiletechtest.feature.transacionsdashboard.data.utils

object GraphUtils {

    fun <T> List<Pair<T, T>>.getUniqueValuesFromPairs(): Set<T> = this
        .map { (a, b) -> listOf(a, b) }
        .flatten()
        .toSet()

    fun <T> List<Pair<T, T>>.getUniqueValuesFromPairs(predicate: (T) -> Boolean): Set<T> = this
        .map { (a, b) -> listOf(a, b) }
        .flatten()
        .filter(predicate)
        .toSet()

    data class Graph<T>(
        val vertices: Set<T>,
        val edges: Map<T, Set<T>>
    ) {
        constructor(init: List<Pair<T, T>>) : this(
            vertices = init.toList().getUniqueValuesFromPairs(),
            edges = init
                .groupBy { it.first }
                .mapValues { it.value.getUniqueValuesFromPairs { x -> x !== it.key } }
                .withDefault { emptySet() }
        )
    }

    fun <T> findPathInGraph(graph: Graph<T>, startNode: T, desired: T): List<Pair<T, T>>? {
        val visitedVertices: MutableSet<T> = mutableSetOf()
        visitedVertices.add(startNode)

        val path = findRecursive(graph, visitedVertices, desired, startNode, startNode)

        return if (path.isEmpty()) null else path.subList(1, path.size)
    }

    private fun <T> findRecursive(
        graph: Graph<T>,
        visitedVertices: MutableSet<T>,
        desired: T,
        last: T,
        current: T
    ): List<Pair<T, T>> {

        if (current == desired) {
            return listOf(last to current)
        }

        val neighbors: Set<T>? = graph.edges[current]?.minus(visitedVertices)
        if (neighbors.isNullOrEmpty()) {
            return emptyList()
        } else {
            neighbors.forEach { currentCandidate ->
                if (visitedVertices.contains(currentCandidate).not()) {
                    visitedVertices.add(currentCandidate)
                    val pathCandidate = findRecursive(
                        graph,
                        visitedVertices,
                        desired,
                        current,
                        currentCandidate
                    )

                    if (pathCandidate.isNotEmpty()) {
                        return listOf(last to current) + pathCandidate
                    }
                }
            }
        }
        return emptyList()
    }
}

