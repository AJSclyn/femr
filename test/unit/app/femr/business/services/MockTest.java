package unit.app.femr.business.services;

import femr.business.services.core.IPatientService;
import femr.business.services.system.PatientService;
import femr.common.ItemModelMapper;
import femr.common.dtos.ServiceResponse;
import femr.common.models.PatientItem;
import femr.data.DataModelMapper;
import femr.data.daos.system.PatientRepository;
import femr.data.models.mysql.Patient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

        //create random patient
        patient = new Patient();

        //re-define mock method calls
        when(mockPatientRepository.retrievePatientById(2)).thenReturn(patient);
        when(mockPatientRepository.savePatient(patient)).thenReturn(patient);
        }

    @Test
    public void deletePatient_nullReasonProvided_errorProduced2() throws Exception {

        //arrange
        ServiceResponse<PatientItem> response;

        //act
        response = patientService.deletePatient(1, 2, null);

        //assert
        verify(mockPatientRepository).retrievePatientById(1);
        //verify(mockPatientRepository.savePatient(patient));
        //        assertFalse(mockPatientRepository.retrievePatientByIdWasCalled);
//        assertFalse(mockPatientRepository.savePatientWasCalled);
        assertTrue(response.hasErrors());
    }


}
