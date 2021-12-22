import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import androidx.annotation.Nullable;

/**
 * Created by LINDA on 8/12/2019.
 */

public class retrieveEmailService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Your code here...




        return super.onStartCommand(intent, flags, startId);
    }
}
