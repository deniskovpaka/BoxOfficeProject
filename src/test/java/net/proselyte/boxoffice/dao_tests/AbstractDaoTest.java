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
import static org.junit.Assert.assertTrue;

/**
 * The
 *
 * @author deniskovpaka
 */
@RunWith(Parameterized.class)
public abstract class AbstractDaoTest<Context> {
    protected Class daoClass;
    protected Identification notPersistedDto;
    public abstract GenericDao dao();
    public abstract Context context();
    private static Integer unique_id = 0;

    public AbstractDaoTest(Class clazz, Identification<Integer> notPersistedDto) {
        this.daoClass = clazz;
        this.notPersistedDto = notPersistedDto;
    }

    @Test
    public void persist()
            throws Exception {
        assertThat(notPersistedDto.getId(), nullValue());
        notPersistedDto = dao().persist(notPersistedDto);
        assertThat(notPersistedDto.getId(), notNullValue());
    }

    @Test
    public void getByPrimaryKey() throws Exception {
        notPersistedDto = dao().persist(notPersistedDto);
        Identification instance = dao().getByPrimaryKey(notPersistedDto.getId());
        assertThat(instance, notNullValue());
    }

//    @Test
//    public void update() throws Exception {
//        notPersistedDto = dao().persist(notPersistedDto);
//        Identification instance = dao().getByPrimaryKey(notPersistedDto.getId());
//    }

    @Test
    public void delete() throws Exception {
        notPersistedDto = dao().persist(notPersistedDto);

        List list = dao().getAll();
        assertThat(list, notNullValue());

        int oldSize = list.size();
        assertThat(oldSize, greaterThan(0));

        dao().delete(notPersistedDto);
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