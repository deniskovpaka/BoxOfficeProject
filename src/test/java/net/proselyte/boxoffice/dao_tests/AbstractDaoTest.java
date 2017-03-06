package net.proselyte.boxoffice.dao_tests;

import net.proselyte.boxoffice.dao.GenericDao;
import net.proselyte.boxoffice.dao.Identification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * The AbstractDaoTest provides set of all methods
 * that need to be tested for Dao instances.
 *
 * @author deniskovpaka
 */
@RunWith(Parameterized.class)
public abstract class AbstractDaoTest<Context> {
    @Parameterized.Parameter(0)
    public Class daoClass;
    @Parameterized.Parameter(1)
    public Identification daoInstance;

    public abstract GenericDao dao();
    public abstract Context context();

    @Test
    public void persist()
            throws Exception {
        assertThat(daoInstance.getId(), nullValue());
        daoInstance = dao().persist(daoInstance);
        assertThat(daoInstance.getId(), notNullValue());
    }

    @Test
    public void getByPrimaryKey() throws Exception {
        daoInstance = dao().persist(daoInstance);
        Identification instance = dao().getByPrimaryKey(daoInstance.getId());
        assertThat(instance, notNullValue());
    }

    @Test
    public void update() throws Exception {
        daoInstance = dao().persist(daoInstance);
        Identification instance = dao().getByPrimaryKey(daoInstance.getId());
        assertThat(instance, notNullValue());
    }

    @Test
    public void delete() throws Exception {
        daoInstance = dao().persist(daoInstance);

        List list = dao().getAll();
        assertThat(list, notNullValue());

        int oldSize = list.size();
        assertThat(oldSize, greaterThan(0));

        dao().delete(daoInstance);
        list = dao().getAll();
        assertThat(list, notNullValue());

        int newSize = list.size();
        assertThat(oldSize - newSize, is(1));
    }

    @Test
    public void getAll() throws Exception {
        List list = dao().getAll();
        assertThat(list, notNullValue());
        assertThat(list.size(), greaterThan(0));
    }
}