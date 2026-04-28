package com.nhom10.aifitnutrition.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.nhom10.aifitnutrition.data.model.UserProfile;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserProfileDao_Impl implements UserProfileDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserProfile> __insertionAdapterOfUserProfile;

  private final EntityDeletionOrUpdateAdapter<UserProfile> __updateAdapterOfUserProfile;

  public UserProfileDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserProfile = new EntityInsertionAdapter<UserProfile>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_profile` (`id`,`name`,`ageYears`,`heightCm`,`weightKg`,`goalType`,`targetWeightKg`,`dailyCalorieGoal`,`proteinGoalG`,`carbsGoalG`,`fatGoalG`,`waterGoalMl`,`activityLevel`,`gender`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProfile entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        statement.bindLong(3, entity.getAgeYears());
        statement.bindDouble(4, entity.getHeightCm());
        statement.bindDouble(5, entity.getWeightKg());
        if (entity.getGoalType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getGoalType());
        }
        statement.bindDouble(7, entity.getTargetWeightKg());
        statement.bindLong(8, entity.getDailyCalorieGoal());
        statement.bindDouble(9, entity.getProteinGoalG());
        statement.bindDouble(10, entity.getCarbsGoalG());
        statement.bindDouble(11, entity.getFatGoalG());
        statement.bindLong(12, entity.getWaterGoalMl());
        if (entity.getActivityLevel() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getActivityLevel());
        }
        if (entity.getGender() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getGender());
        }
      }
    };
    this.__updateAdapterOfUserProfile = new EntityDeletionOrUpdateAdapter<UserProfile>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `user_profile` SET `id` = ?,`name` = ?,`ageYears` = ?,`heightCm` = ?,`weightKg` = ?,`goalType` = ?,`targetWeightKg` = ?,`dailyCalorieGoal` = ?,`proteinGoalG` = ?,`carbsGoalG` = ?,`fatGoalG` = ?,`waterGoalMl` = ?,`activityLevel` = ?,`gender` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserProfile entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        statement.bindLong(3, entity.getAgeYears());
        statement.bindDouble(4, entity.getHeightCm());
        statement.bindDouble(5, entity.getWeightKg());
        if (entity.getGoalType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getGoalType());
        }
        statement.bindDouble(7, entity.getTargetWeightKg());
        statement.bindLong(8, entity.getDailyCalorieGoal());
        statement.bindDouble(9, entity.getProteinGoalG());
        statement.bindDouble(10, entity.getCarbsGoalG());
        statement.bindDouble(11, entity.getFatGoalG());
        statement.bindLong(12, entity.getWaterGoalMl());
        if (entity.getActivityLevel() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getActivityLevel());
        }
        if (entity.getGender() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getGender());
        }
        statement.bindLong(15, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final UserProfile profile, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserProfile.insert(profile);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final UserProfile profile, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUserProfile.handle(profile);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<UserProfile> getUserProfile() {
    final String _sql = "SELECT * FROM user_profile WHERE id = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_profile"}, new Callable<UserProfile>() {
      @Override
      @Nullable
      public UserProfile call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAgeYears = CursorUtil.getColumnIndexOrThrow(_cursor, "ageYears");
          final int _cursorIndexOfHeightCm = CursorUtil.getColumnIndexOrThrow(_cursor, "heightCm");
          final int _cursorIndexOfWeightKg = CursorUtil.getColumnIndexOrThrow(_cursor, "weightKg");
          final int _cursorIndexOfGoalType = CursorUtil.getColumnIndexOrThrow(_cursor, "goalType");
          final int _cursorIndexOfTargetWeightKg = CursorUtil.getColumnIndexOrThrow(_cursor, "targetWeightKg");
          final int _cursorIndexOfDailyCalorieGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyCalorieGoal");
          final int _cursorIndexOfProteinGoalG = CursorUtil.getColumnIndexOrThrow(_cursor, "proteinGoalG");
          final int _cursorIndexOfCarbsGoalG = CursorUtil.getColumnIndexOrThrow(_cursor, "carbsGoalG");
          final int _cursorIndexOfFatGoalG = CursorUtil.getColumnIndexOrThrow(_cursor, "fatGoalG");
          final int _cursorIndexOfWaterGoalMl = CursorUtil.getColumnIndexOrThrow(_cursor, "waterGoalMl");
          final int _cursorIndexOfActivityLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "activityLevel");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final UserProfile _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpAgeYears;
            _tmpAgeYears = _cursor.getInt(_cursorIndexOfAgeYears);
            final float _tmpHeightCm;
            _tmpHeightCm = _cursor.getFloat(_cursorIndexOfHeightCm);
            final float _tmpWeightKg;
            _tmpWeightKg = _cursor.getFloat(_cursorIndexOfWeightKg);
            final String _tmpGoalType;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmpGoalType = null;
            } else {
              _tmpGoalType = _cursor.getString(_cursorIndexOfGoalType);
            }
            final float _tmpTargetWeightKg;
            _tmpTargetWeightKg = _cursor.getFloat(_cursorIndexOfTargetWeightKg);
            final int _tmpDailyCalorieGoal;
            _tmpDailyCalorieGoal = _cursor.getInt(_cursorIndexOfDailyCalorieGoal);
            final float _tmpProteinGoalG;
            _tmpProteinGoalG = _cursor.getFloat(_cursorIndexOfProteinGoalG);
            final float _tmpCarbsGoalG;
            _tmpCarbsGoalG = _cursor.getFloat(_cursorIndexOfCarbsGoalG);
            final float _tmpFatGoalG;
            _tmpFatGoalG = _cursor.getFloat(_cursorIndexOfFatGoalG);
            final int _tmpWaterGoalMl;
            _tmpWaterGoalMl = _cursor.getInt(_cursorIndexOfWaterGoalMl);
            final String _tmpActivityLevel;
            if (_cursor.isNull(_cursorIndexOfActivityLevel)) {
              _tmpActivityLevel = null;
            } else {
              _tmpActivityLevel = _cursor.getString(_cursorIndexOfActivityLevel);
            }
            final String _tmpGender;
            if (_cursor.isNull(_cursorIndexOfGender)) {
              _tmpGender = null;
            } else {
              _tmpGender = _cursor.getString(_cursorIndexOfGender);
            }
            _result = new UserProfile(_tmpId,_tmpName,_tmpAgeYears,_tmpHeightCm,_tmpWeightKg,_tmpGoalType,_tmpTargetWeightKg,_tmpDailyCalorieGoal,_tmpProteinGoalG,_tmpCarbsGoalG,_tmpFatGoalG,_tmpWaterGoalMl,_tmpActivityLevel,_tmpGender);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getUserProfileOnce(final Continuation<? super UserProfile> $completion) {
    final String _sql = "SELECT * FROM user_profile WHERE id = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserProfile>() {
      @Override
      @Nullable
      public UserProfile call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAgeYears = CursorUtil.getColumnIndexOrThrow(_cursor, "ageYears");
          final int _cursorIndexOfHeightCm = CursorUtil.getColumnIndexOrThrow(_cursor, "heightCm");
          final int _cursorIndexOfWeightKg = CursorUtil.getColumnIndexOrThrow(_cursor, "weightKg");
          final int _cursorIndexOfGoalType = CursorUtil.getColumnIndexOrThrow(_cursor, "goalType");
          final int _cursorIndexOfTargetWeightKg = CursorUtil.getColumnIndexOrThrow(_cursor, "targetWeightKg");
          final int _cursorIndexOfDailyCalorieGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyCalorieGoal");
          final int _cursorIndexOfProteinGoalG = CursorUtil.getColumnIndexOrThrow(_cursor, "proteinGoalG");
          final int _cursorIndexOfCarbsGoalG = CursorUtil.getColumnIndexOrThrow(_cursor, "carbsGoalG");
          final int _cursorIndexOfFatGoalG = CursorUtil.getColumnIndexOrThrow(_cursor, "fatGoalG");
          final int _cursorIndexOfWaterGoalMl = CursorUtil.getColumnIndexOrThrow(_cursor, "waterGoalMl");
          final int _cursorIndexOfActivityLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "activityLevel");
          final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
          final UserProfile _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpAgeYears;
            _tmpAgeYears = _cursor.getInt(_cursorIndexOfAgeYears);
            final float _tmpHeightCm;
            _tmpHeightCm = _cursor.getFloat(_cursorIndexOfHeightCm);
            final float _tmpWeightKg;
            _tmpWeightKg = _cursor.getFloat(_cursorIndexOfWeightKg);
            final String _tmpGoalType;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmpGoalType = null;
            } else {
              _tmpGoalType = _cursor.getString(_cursorIndexOfGoalType);
            }
            final float _tmpTargetWeightKg;
            _tmpTargetWeightKg = _cursor.getFloat(_cursorIndexOfTargetWeightKg);
            final int _tmpDailyCalorieGoal;
            _tmpDailyCalorieGoal = _cursor.getInt(_cursorIndexOfDailyCalorieGoal);
            final float _tmpProteinGoalG;
            _tmpProteinGoalG = _cursor.getFloat(_cursorIndexOfProteinGoalG);
            final float _tmpCarbsGoalG;
            _tmpCarbsGoalG = _cursor.getFloat(_cursorIndexOfCarbsGoalG);
            final float _tmpFatGoalG;
            _tmpFatGoalG = _cursor.getFloat(_cursorIndexOfFatGoalG);
            final int _tmpWaterGoalMl;
            _tmpWaterGoalMl = _cursor.getInt(_cursorIndexOfWaterGoalMl);
            final String _tmpActivityLevel;
            if (_cursor.isNull(_cursorIndexOfActivityLevel)) {
              _tmpActivityLevel = null;
            } else {
              _tmpActivityLevel = _cursor.getString(_cursorIndexOfActivityLevel);
            }
            final String _tmpGender;
            if (_cursor.isNull(_cursorIndexOfGender)) {
              _tmpGender = null;
            } else {
              _tmpGender = _cursor.getString(_cursorIndexOfGender);
            }
            _result = new UserProfile(_tmpId,_tmpName,_tmpAgeYears,_tmpHeightCm,_tmpWeightKg,_tmpGoalType,_tmpTargetWeightKg,_tmpDailyCalorieGoal,_tmpProteinGoalG,_tmpCarbsGoalG,_tmpFatGoalG,_tmpWaterGoalMl,_tmpActivityLevel,_tmpGender);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
