import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Andrei Pazniak on 4/25/2017.
 */
public class Temp {
    @Test
    public void findArticles(){
    String SearchText="Aaaa bbb cc";
    String CompareText="ff";
    //SearchText.compareTo(CompareText);
        Assert.assertTrue("texts are the same", SearchText.indexOf(CompareText)>=0);

    }
}
