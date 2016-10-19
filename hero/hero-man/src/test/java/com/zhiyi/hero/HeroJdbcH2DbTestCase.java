package com.zhiyi.hero;

import org.dbunit.JdbcBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hrs on 2014/10/22.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-spring-config.xml")
@Transactional
public abstract class HeroJdbcH2DbTestCase extends JdbcBasedDBTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    @Override
    protected String getConnectionUrl() {
        return "jdbc:h2:mem:test;MODE=MySQL;IGNORECASE=TRUE";
    }

    @Override
    protected String getDriverClass() {
        return "org.h2.Driver";
    }

    @Override
    protected String getUsername() {
        return "sa";
    }

    @Override
    protected String getPassword() {
        return "";
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        String file = getClass().getSimpleName() + ".xml";
        return new XmlDataSet(this.getClass().getResourceAsStream(file));
    }
}
