package pl.xxiiliixx.MineCord.Manager;

import pl.xxiiliixx.MineCord.Manager.Verification.VerificationData;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class VerificationCodesManager {

    static Map<Integer, VerificationData> codes = new HashMap<>();

    public void AddCode(String userId, int code) {
        VerificationData verificationData = new VerificationData();

        verificationData.DateTime = LocalDateTime.now();
        verificationData.UserId = userId;

        codes.put(code, verificationData);

    }

    public boolean isCorrectCode(int code) {
        if (codes.containsKey(code)) {
            return true;
        }

        return false;

    }

    public VerificationData getValue(int code) {
        return codes.get(code);
    }

    public void Remove(int code) {
        if (codes.containsKey(code)) {
            codes.remove(code);
        }

    }

}
