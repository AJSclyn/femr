package unit.app.femr.business.services;

import femr.business.services.core.IPatientService;
import femr.business.services.system.PatientService;
import femr.common.ItemModelMapper;
import femr.common.dtos.ServiceResponse;
import femr.common.models.PatientItem;
import femr.data.DataModelMapper;
import femr.data.daos.system.PatientRepository;
import femr.data.models.mysql.Patient;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by AJ on 4/30/2017.
 */


public class MockTest {
    private IPatientService patientService;
    private Patient patient = null;
//    private Integer patientId = 1;

    @Mock
    PatientRepository mockPatientRepository;
    DataModelMapper mockDataModelMapper;
    ItemModelMapper mockItemModelMapper;

    @Before
    /**
     * Create mock objects
     */
        public void create(){
            //initialize mocks
            initMocks(this);

            //initialize service with mock objects
            patientService = new PatientService(mockPatientRepository, mockDataModelMapper, mockItemModelMapper);

            //re-define mock method calls (stubs)
            when(mockPatientRepository.retrievePatientById(2)).thenReturn(patient);
            when(mockPatientRepository.savePatient(patient)).thenReturn(patient);
        }

    @Test
    public void deletePatient_nullReasonProvided_errorProduced2() throws Exception {

        //arrange
        ServiceResponse<PatientItem> response;

        //act
        response = patientService.deletePatient(1, 2, null);

        //verify
        verify(mockPatientRepository, never()).retrievePatientById(1);
        verify(mockPatientRepository, never()).savePatient(patient);
        assertTrue(response.hasErrors());
    }

    @Test
    public void deletePatient_emptyReasonProvided_errorProduced() throws Exception {

        //arrange
        ServiceResponse<PatientItem> response;

        //act
        response = patientService.deletePatient(1, 2, "");

        //verify
        verify(mockPatientRepository, never()).retrievePatientById(1);
        verify(mockPatientRepository, never()).savePatient(patient);
        //assert
        assertTrue(response.hasErrors());
    }

    @Test
    public void deletePatient_parametersProvided_parametersSaved() throws Exception {
        Patient mockPatient = Mockito.mock(Patient.class);
        mockPatient.setIsDeleted(DateTime.now());
        mockPatient.setDeletedByUserId(2);
        mockPatient.setReasonDeleted("fake reason");
        when(mockPatientRepository.savePatient(patient)).thenReturn(mockPatient);


        //To-do-
        // Should we mock patient ?

        //arrange
        ServiceResponse<PatientItem> response;

//        when(mockPatientRepository.savePatient(mockPatient).set
        //act
        response = patientService.deletePatient(1, 2, "fake reason");

        //verify
        verify(mockPatientRepository).retrievePatientById(1);
        verify(mockPatientRepository).savePatient(mockPatient);
        //make sure patient repository was called with proper reason
        //assert
        assertEquals(mockPatientRepository.savePatient(mockPatient).getReasonDeleted(), "fake reason");
        //make sure proper user ID is logged
        assertEquals(mockPatientRepository.savePatient(mockPatient).getDeletedByUserId(), (Integer)2);
        //assertEquals(mockPatientRepository.mockPatient.getId(), 1);

        assertFalse(response.hasErrors());
    }
}
