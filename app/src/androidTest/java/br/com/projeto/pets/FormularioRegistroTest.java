package br.com.projeto.pets;

import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.PatternsCompat;
import android.text.TextUtils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class FormularioRegistroTest {

    @Test
    public void testaFormularioDeRegistroModificadoAllOK() throws Exception {

        String username = "username";
        String password = "username";
        String email1 = "user@name.com";
        String email2 = "user@name.com";


        assertEquals(TextUtils.isEmpty(username.trim()), false);
        assertEquals(username.length() < 3, false);

        CharSequence cs = String.valueOf(username.charAt(0));
        assertEquals(StringUtils.isNumeric(cs), false);
        assertEquals(StringUtils.isAlphanumeric(cs), true);

        assertEquals(TextUtils.isEmpty(password.trim()), false);
        assertEquals(password.length() < 6, false);

        assertEquals(TextUtils.isEmpty(email1.trim()), false);
        assertEquals(PatternsCompat.EMAIL_ADDRESS.matcher(email1).matches(), true);

        assertEquals(TextUtils.isEmpty(email2.trim()), false);
        assertEquals(PatternsCompat.EMAIL_ADDRESS.matcher(email2).matches(), true);

        assertEquals(email1.equals(email2), true);

    }

    @Test//usuario vazio
    public void testaFormularioDeRegistroModificadoUserProblem1() throws Exception {

        String username = "";

        assertEquals(TextUtils.isEmpty(username.trim()), true);
        assertEquals(username.length() < 3, true);
    }

    @Test//usuario começando com numero
    public void testaFormularioDeRegistroModificadoUserProblem2() throws Exception {

        String username = "14dylol";

        CharSequence cs = String.valueOf(username.charAt(0));
        assertEquals(StringUtils.isNumeric(cs), true);

    }

    @Test//usuario começando com algo nao alphanumerico
    public void testaFormularioDeRegistroModificadoUserProblem3() throws Exception {

        String username = "#dandy";

        CharSequence cs = String.valueOf(username.charAt(0));
        assertEquals(StringUtils.isAlphanumeric(cs), false);

    }

    @Test//senha vazio
    public void testaFormularioDeRegistroModificadoUserProblem4() throws Exception {

        String password = "";

        assertEquals(TextUtils.isEmpty(password.trim()), true);
        assertEquals(password.length() < 6, true);
    }

    @Test//email vazio
    public void testaFormularioDeRegistroModificadoUserProblem5() throws Exception {

        String email1 = "";

        assertEquals(TextUtils.isEmpty(email1.trim()), true);
    }

    @Test//email naovalid
    public void testaFormularioDeRegistroModificadoUserProblem6() throws Exception {

        String email1 = "!samir@email.com";
        String email2 = "http://email.com";
        String email3 = "@@email.com";

        assertEquals(PatternsCompat.EMAIL_ADDRESS.matcher(email1).matches(), false);
        assertEquals(PatternsCompat.EMAIL_ADDRESS.matcher(email2).matches(), false);
        assertEquals(PatternsCompat.EMAIL_ADDRESS.matcher(email3).matches(), false);
    }

    @Test//email 1 diferente do email 2
    public void testaFormularioDeRegistroModificadoUserProblem7() throws Exception {

        String email1 = "em@ail.com";
        String email2 = "em@ail.com.br";

        assertEquals(email1.equals(email2), false);
    }

}
