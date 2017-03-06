package net.proselyte.boxoffice.controller_tests;

import net.proselyte.boxoffice.controller.request_handlers.InitializeRequestHandler;
import net.proselyte.boxoffice.dao.DaoFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

import static net.proselyte.boxoffice.controller.request_handlers.RequestHandler.JSP_USER_INPUT_ATTRIBUTE;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.verifyPrivate;

@RunWith(PowerMockRunner.class)
@PrepareForTest({InitializeRequestHandler.class})
public class InitializeRequestHandlerTest {
    @Mock
    private DaoFactory daoFactoryMock;
    @Mock
    private Connection connectionMock;

    private InitializeRequestHandler handlerSpy;
    private HttpServletRequest requestMock;
    private HttpServletResponse responseMock;

    private final String BY_ID_METHOD_NAME          = "choosePlayByID";
    private final String USER_CHOICE_BY_ID          = "1";
    private final String BY_NAME_METHOD_NAME        = "choosePlayByName";
    private final String USER_CHOICE_BY_NAME        = "2";
    private final int WANTED_NUMBER_OF_INVOCATION   = 1;

    @Before
    public void setUp()
            throws Exception {
        requestMock = Mockito.mock(HttpServletRequest.class);
        responseMock = Mockito.mock(HttpServletResponse.class);
        handlerSpy = PowerMockito.spy(new InitializeRequestHandler(daoFactoryMock, connectionMock));
    }

    @Test
    public void processRequestById()
            throws Exception {
        PowerMockito.when(requestMock.getParameter(JSP_USER_INPUT_ATTRIBUTE)).thenReturn(new String(USER_CHOICE_BY_ID));
        doNothing().when(handlerSpy, BY_ID_METHOD_NAME, anyObject(), anyObject());
        handlerSpy.processRequest(requestMock, responseMock);
        verifyPrivate(handlerSpy, times(WANTED_NUMBER_OF_INVOCATION)).invoke(BY_ID_METHOD_NAME, anyObject(), anyObject());
    }

    @Test
    public void processRequestByName()
            throws Exception {
        PowerMockito.when(requestMock.getParameter(JSP_USER_INPUT_ATTRIBUTE)).thenReturn(new String(USER_CHOICE_BY_NAME));
        doNothing().when(handlerSpy, BY_NAME_METHOD_NAME, anyObject(), anyObject());
        handlerSpy.processRequest(requestMock, responseMock);
        verifyPrivate(handlerSpy, times(WANTED_NUMBER_OF_INVOCATION)).invoke(BY_NAME_METHOD_NAME, anyObject(), anyObject());
    }
}