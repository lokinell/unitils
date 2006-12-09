/*
 * Copyright 2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.unitils.dbmaintainer.constraints;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.dbutils.DbUtils;
import org.unitils.UnitilsJUnit3;
import org.unitils.core.ConfigurationLoader;
import org.unitils.database.annotations.DatabaseTest;
import org.unitils.database.annotations.TestDataSource;
import org.unitils.dbmaintainer.handler.StatementHandler;
import org.unitils.dbmaintainer.util.DatabaseModuleConfigUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Test class for the ConstraintsDisabler. This test is independent of the dbms that is used. The database dialect that
 * is tested depends on the configuration in test/resources/unitils.properties
 */
@DatabaseTest
public class ConstraintsDisablerTest extends UnitilsJUnit3 {

    /**
     * The tested object
     */
    private ConstraintsDisabler constraintsDisabler;

    /**
     * DataSource for the test database, is injected
     */
    @TestDataSource
    private javax.sql.DataSource dataSource;

    /**
     * Test fixture. Configures the ConstraintsDisabler with the implementation that matches the configured database
     * dialect
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Configuration configuration = new ConfigurationLoader().loadConfiguration();
        StatementHandler statementHandler = DatabaseModuleConfigUtils.getConfiguredStatementHandlerInstance(configuration,
                dataSource);

        constraintsDisabler = DatabaseModuleConfigUtils.getConfiguredDatabaseTaskInstance(ConstraintsDisabler.class,
                configuration, dataSource, statementHandler);

        dropTestTables();
        createTestTables();
    }

    /**
     * Drops the test tables, to avoid influencing other tests
     *
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        dropTestTables();

        super.tearDown();
    }

    /**
     * Creates the test tables
     *
     * @throws SQLException
     */
    private void createTestTables() throws SQLException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = dataSource.getConnection();
            st = conn.createStatement();
            st.execute("create table table1 (col1 varchar(10) primary key, col2 varchar(12) not null)");
            st.execute("create table table2 (col1 varchar(10), foreign key (col1) references table1(col1))");
        } finally {
            DbUtils.closeQuietly(conn, st, null);
        }
    }

    /**
     * Drops the test tables
     *
     * @throws SQLException
     */
    private void dropTestTables() throws SQLException {
        Connection conn = null;
        Statement st = null;
        try {
            conn = dataSource.getConnection();
            st = conn.createStatement();
            try {
                st.execute("drop table table2 cascade");
            } catch (SQLException e) {
                // Ignored
            }
            try {
                st.execute("drop table table1 cascade");
            } catch (SQLException e) {
                // Ignored
            }
        } finally {
            DbUtils.closeQuietly(conn, st, null);
        }
    }

    /**
     * Tests whether foreign key constraints are correctly disabled
     *
     * @throws SQLException
     */
    public void testDisableConstraints_foreignKey() throws Exception {
        Connection conn = null;
        try {
            try {
                conn = dataSource.getConnection();
                insertForeignKeyViolation(conn);
                fail("SQLException should have been thrown");
            } catch (SQLException e) {
                // Foreign key violation, should throw SQLException
            }

            constraintsDisabler.disableConstraints();
            constraintsDisabler.disableConstraintsOnConnection(conn);
            // Should not throw exception anymore
            insertForeignKeyViolation(conn);
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * Performs an insert that violates the foreign key constraint that table2.col1 has on table1
     *
     * @param conn
     * @throws SQLException Is thrown when the foreign key constraint is enabled
     */
    private void insertForeignKeyViolation(Connection conn) throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            st.executeUpdate("insert into table2 values ('test')");
        } finally {
            DbUtils.closeQuietly(st);
        }
    }

    /**
     * Tests whether not-null constraints are correctly disabled
     *
     * @throws SQLException
     */
    public void testDisableConstraints_notNull() throws Exception {
        Connection conn = null;
        try {
            try {
                conn = dataSource.getConnection();
                insertNotNullViolation(conn);
                fail("SQLException should have been thrown");
            } catch (SQLException e) {
                // Foreign key violation, should throw SQLException
            }

            constraintsDisabler.disableConstraints();
            constraintsDisabler.disableConstraintsOnConnection(conn);
            // Should not throw exception anymore
            insertNotNullViolation(conn);
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * Performs an insert on table1 that violates the not-null constraint on col2
     *
     * @param conn
     * @throws SQLException Is thrown when the not null constraint is enabled
     */
    private void insertNotNullViolation(Connection conn) throws SQLException {
        Statement st = null;
        try {
            st = conn.createStatement();
            st.execute("insert into table1 values ('test', null)");
        } finally {
            DbUtils.closeQuietly(st);
        }
    }
}