package uk.co.grahamcox.elloria.jdbc

/**
 * Representation of a database table
 */
open data class Table() {
    /**
     * Representation of a column in a table
     */
    data class Column(name: String, type: Class<*>)

    /** The collection of all the columns in the table */
    private var columns = hashSetOf<Column>()

    /**
     * Add a new column to the table
     * @param name the name of the column
     * @param type The type of the column
     */
    protected fun column(name: String, type: Class<*>): Column {
        val column = Column(name, type)
        columns.add(column)
        return column
    }

    /**
     * Get all of the columns in the table
     * @return the set of columns in the table
     */
    fun columns(): Set<Column> = columns
}