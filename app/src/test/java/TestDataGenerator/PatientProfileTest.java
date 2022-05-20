package TestDataGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PatientProfileTest {
    PatientProfile pf = new PatientProfile();
    @Test
    public void createPatientProfileTest() {

        String[] pProfile = pf.createPatientProfile();
        assertEquals(pProfile.length, 11);

        for(int i=0;i<pProfile.length;i++)
        {
            assertNotNull(pProfile[i]);
        }
    }

    @Test
    void getPatientProfileListTest() {
        List<String[]> pProfileList = pf.getPatientProfileList(2);
        assertEquals(pProfileList.size(),3);
        assertEquals(pProfileList.get(0), Constant.registrationHeader);
    }

    @Mock
    private PatientProfile patientProfile = new PatientProfile();

    @Test
    void writePatientProfileInCSVTest() throws IOException {
        patientProfile.writePatientProfileInCSV(1);
        verify(patientProfile, Mockito.times(1)).writePatientProfileInCSV(1);
    }

}