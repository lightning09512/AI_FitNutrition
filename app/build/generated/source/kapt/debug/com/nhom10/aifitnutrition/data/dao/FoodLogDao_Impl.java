package com.nhom10.aifitnutrition.data.dao;

import android.database.Cursor;
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
import com.nhom10.aifitnutrition.data.model.FoodLog;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FoodLogDao_Impl implements FoodLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FoodLog> __insertionAdapterOfFoodLog;

  private final EntityDeletionOrUpdateAdapter<FoodLog> __deletionAdapterOfFoodLog;

  private final EntityDeletionOrUpdateAdapter<FoodLog> __updateAdapterOfFoodLog;

  public FoodLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFoodLog = new EntityInsertionAdapter<FoodLog>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `food_logs` (`id`,`mealType`,`foodName`,`calories`,`proteinG`,`carbsG`,`fatG`,`portionGrams`,`imageUri`,`date`,`timestamp`,`isAiAnalyzed`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FoodLog entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getMealType() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getMealType());
        }
        if (entity.getFoodName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getFoodName());
        }
        statement.bindLong(4, entity.getCalories());
        statement.bindDouble(5, entity.getProteinG());
        statement.bindDouble(6, entity.getCarbsG());
        statement.bindDouble(7, entity.getFatG());
        statement.bindDouble(8, entity.getPortionGrams());
        if (entity.getImageUri() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getImageUri());
        }
        if (entity.getDate() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getDate());
        }
        statement.bindLong(11, entity.getTimestamp());
        final int _tmp = entity.isAiAnalyzed() ? 1 : 0;
        statement.bindLong(12, _tmp);
      }
    };
    this.__deletionAdapterOfFoodLog = new EntityDeletionOrUpdateAdapter<FoodLog>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `food_logs` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FoodLog entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfFoodLog = new EntityDeletionOrUpdateAdapter<FoodLog>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `food_logs` SET `id` = ?,`mealType` = ?,`foodName` = ?,`calories` = ?,`proteinG` = ?,`carbsG` = ?,`fatG` = ?,`portionGrams` = ?,`imageUri` = ?,`date` = ?,`timestamp` = ?,`isAiAnalyzed` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FoodLog entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getMealType() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getMealType());
        }
        if (entity.getFoodName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getFoodName());
        }
        statement.bindLong(4, entity.getCalories());
        statement.bindDouble(5, entity.getProteinG());
        statement.bindDouble(6, entity.getCarbsG());
        statement.bindDouble(7, entity.getFatG());
        statement.bindDouble(8, entity.getPortionGrams());
        if (entity.getImageUri() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getImageUri());
        }
        if (entity.getDate() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getDate());
        }
        statement.bindLong(11, entity.getTimestamp());
        final int _tmp = entity.isAiAnalyzed() ? 1 : 0;
        statement.bindLong(12, _tmp);
        statement.bindLong(13, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final FoodLog foodLog, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfFoodLog.insertAndReturnId(foodLog);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final FoodLog foodLog, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfFoodLog.handle(foodLog);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final FoodLog foodLog, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfFoodLog.handle(foodLog);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<FoodLog>> getFoodLogsByDate(final String date) {
    final String _sql = "SELECT * FROM food_logs WHERE date = ? ORDER BY timestamp ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"food_logs"}, new Callable<List<FoodLog>>() {
      @Override
      @NonNull
      public List<FoodLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMealType = CursorUtil.getColumnIndexOrThrow(_cursor, "mealType");
          final int _cursorIndexOfFoodName = CursorUtil.getColumnIndexOrThrow(_cursor, "foodName");
          final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
          final int _cursorIndexOfProteinG = CursorUtil.getColumnIndexOrThrow(_cursor, "proteinG");
          final int _cursorIndexOfCarbsG = CursorUtil.getColumnIndexOrThrow(_cursor, "carbsG");
          final int _cursorIndexOfFatG = CursorUtil.getColumnIndexOrThrow(_cursor, "fatG");
          final int _cursorIndexOfPortionGrams = CursorUtil.getColumnIndexOrThrow(_cursor, "portionGrams");
          final int _cursorIndexOfImageUri = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUri");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfIsAiAnalyzed = CursorUtil.getColumnIndexOrThrow(_cursor, "isAiAnalyzed");
          final List<FoodLog> _result = new ArrayList<FoodLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FoodLog _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpMealType;
            if (_cursor.isNull(_cursorIndexOfMealType)) {
              _tmpMealType = null;
            } else {
              _tmpMealType = _cursor.getString(_cursorIndexOfMealType);
            }
            final String _tmpFoodName;
            if (_cursor.isNull(_cursorIndexOfFoodName)) {
              _tmpFoodName = null;
            } else {
              _tmpFoodName = _cursor.getString(_cursorIndexOfFoodName);
            }
            final int _tmpCalories;
            _tmpCalories = _cursor.getInt(_cursorIndexOfCalories);
            final float _tmpProteinG;
            _tmpProteinG = _cursor.getFloat(_cursorIndexOfProteinG);
            final float _tmpCarbsG;
            _tmpCarbsG = _cursor.getFloat(_cursorIndexOfCarbsG);
            final float _tmpFatG;
            _tmpFatG = _cursor.getFloat(_cursorIndexOfFatG);
            final float _tmpPortionGrams;
            _tmpPortionGrams = _cursor.getFloat(_cursorIndexOfPortionGrams);
            final String _tmpImageUri;
            if (_cursor.isNull(_cursorIndexOfImageUri)) {
              _tmpImageUri = null;
            } else {
              _tmpImageUri = _cursor.getString(_cursorIndexOfImageUri);
            }
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final boolean _tmpIsAiAnalyzed;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsAiAnalyzed);
            _tmpIsAiAnalyzed = _tmp != 0;
            _item = new FoodLog(_tmpId,_tmpMealType,_tmpFoodName,_tmpCalories,_tmpProteinG,_tmpCarbsG,_tmpFatG,_tmpPortionGrams,_tmpImageUri,_tmpDate,_tmpTimestamp,_tmpIsAiAnalyzed);
            _result.add(_item);
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
  public Flow<Integer> getTotalCaloriesForDate(final String date) {
    final String _sql = "SELECT SUM(calories) FROM food_logs WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"food_logs"}, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
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
  public Flow<Float> getTotalProteinForDate(final String date) {
    final String _sql = "SELECT SUM(proteinG) FROM food_logs WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"food_logs"}, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
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
  public Flow<Float> getTotalCarbsForDate(final String date) {
    final String _sql = "SELECT SUM(carbsG) FROM food_logs WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"food_logs"}, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
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
  public Flow<Float> getTotalFatForDate(final String date) {
    final String _sql = "SELECT SUM(fatG) FROM food_logs WHERE date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"food_logs"}, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
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
  public Flow<List<DateCalories>> getWeeklyCalories(final String startDate) {
    final String _sql = "SELECT date, SUM(calories) as totalCals FROM food_logs WHERE date >= ? GROUP BY date ORDER BY date ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (startDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, startDate);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"food_logs"}, new Callable<List<DateCalories>>() {
      @Override
      @NonNull
      public List<DateCalories> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = 0;
          final int _cursorIndexOfTotalCals = 1;
          final List<DateCalories> _result = new ArrayList<DateCalories>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DateCalories _item;
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final int _tmpTotalCals;
            _tmpTotalCals = _cursor.getInt(_cursorIndexOfTotalCals);
            _item = new DateCalories(_tmpDate,_tmpTotalCals);
            _result.add(_item);
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
  public Flow<List<FoodLog>> getFoodLogsByMeal(final String mealType, final String date) {
    final String _sql = "SELECT * FROM food_logs WHERE mealType = ? AND date = ? ORDER BY timestamp ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (mealType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, mealType);
    }
    _argIndex = 2;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"food_logs"}, new Callable<List<FoodLog>>() {
      @Override
      @NonNull
      public List<FoodLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMealType = CursorUtil.getColumnIndexOrThrow(_cursor, "mealType");
          final int _cursorIndexOfFoodName = CursorUtil.getColumnIndexOrThrow(_cursor, "foodName");
          final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
          final int _cursorIndexOfProteinG = CursorUtil.getColumnIndexOrThrow(_cursor, "proteinG");
          final int _cursorIndexOfCarbsG = CursorUtil.getColumnIndexOrThrow(_cursor, "carbsG");
          final int _cursorIndexOfFatG = CursorUtil.getColumnIndexOrThrow(_cursor, "fatG");
          final int _cursorIndexOfPortionGrams = CursorUtil.getColumnIndexOrThrow(_cursor, "portionGrams");
          final int _cursorIndexOfImageUri = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUri");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfIsAiAnalyzed = CursorUtil.getColumnIndexOrThrow(_cursor, "isAiAnalyzed");
          final List<FoodLog> _result = new ArrayList<FoodLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FoodLog _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpMealType;
            if (_cursor.isNull(_cursorIndexOfMealType)) {
              _tmpMealType = null;
            } else {
              _tmpMealType = _cursor.getString(_cursorIndexOfMealType);
            }
            final String _tmpFoodName;
            if (_cursor.isNull(_cursorIndexOfFoodName)) {
              _tmpFoodName = null;
            } else {
              _tmpFoodName = _cursor.getString(_cursorIndexOfFoodName);
            }
            final int _tmpCalories;
            _tmpCalories = _cursor.getInt(_cursorIndexOfCalories);
            final float _tmpProteinG;
            _tmpProteinG = _cursor.getFloat(_cursorIndexOfProteinG);
            final float _tmpCarbsG;
            _tmpCarbsG = _cursor.getFloat(_cursorIndexOfCarbsG);
            final float _tmpFatG;
            _tmpFatG = _cursor.getFloat(_cursorIndexOfFatG);
            final float _tmpPortionGrams;
            _tmpPortionGrams = _cursor.getFloat(_cursorIndexOfPortionGrams);
            final String _tmpImageUri;
            if (_cursor.isNull(_cursorIndexOfImageUri)) {
              _tmpImageUri = null;
            } else {
              _tmpImageUri = _cursor.getString(_cursorIndexOfImageUri);
            }
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final boolean _tmpIsAiAnalyzed;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsAiAnalyzed);
            _tmpIsAiAnalyzed = _tmp != 0;
            _item = new FoodLog(_tmpId,_tmpMealType,_tmpFoodName,_tmpCalories,_tmpProteinG,_tmpCarbsG,_tmpFatG,_tmpPortionGrams,_tmpImageUri,_tmpDate,_tmpTimestamp,_tmpIsAiAnalyzed);
            _result.add(_item);
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
  public Flow<List<FoodLog>> getRecentFoodLogs() {
    final String _sql = "SELECT * FROM food_logs ORDER BY timestamp DESC LIMIT 20";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"food_logs"}, new Callable<List<FoodLog>>() {
      @Override
      @NonNull
      public List<FoodLog> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMealType = CursorUtil.getColumnIndexOrThrow(_cursor, "mealType");
          final int _cursorIndexOfFoodName = CursorUtil.getColumnIndexOrThrow(_cursor, "foodName");
          final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
          final int _cursorIndexOfProteinG = CursorUtil.getColumnIndexOrThrow(_cursor, "proteinG");
          final int _cursorIndexOfCarbsG = CursorUtil.getColumnIndexOrThrow(_cursor, "carbsG");
          final int _cursorIndexOfFatG = CursorUtil.getColumnIndexOrThrow(_cursor, "fatG");
          final int _cursorIndexOfPortionGrams = CursorUtil.getColumnIndexOrThrow(_cursor, "portionGrams");
          final int _cursorIndexOfImageUri = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUri");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfIsAiAnalyzed = CursorUtil.getColumnIndexOrThrow(_cursor, "isAiAnalyzed");
          final List<FoodLog> _result = new ArrayList<FoodLog>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FoodLog _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpMealType;
            if (_cursor.isNull(_cursorIndexOfMealType)) {
              _tmpMealType = null;
            } else {
              _tmpMealType = _cursor.getString(_cursorIndexOfMealType);
            }
            final String _tmpFoodName;
            if (_cursor.isNull(_cursorIndexOfFoodName)) {
              _tmpFoodName = null;
            } else {
              _tmpFoodName = _cursor.getString(_cursorIndexOfFoodName);
            }
            final int _tmpCalories;
            _tmpCalories = _cursor.getInt(_cursorIndexOfCalories);
            final float _tmpProteinG;
            _tmpProteinG = _cursor.getFloat(_cursorIndexOfProteinG);
            final float _tmpCarbsG;
            _tmpCarbsG = _cursor.getFloat(_cursorIndexOfCarbsG);
            final float _tmpFatG;
            _tmpFatG = _cursor.getFloat(_cursorIndexOfFatG);
            final float _tmpPortionGrams;
            _tmpPortionGrams = _cursor.getFloat(_cursorIndexOfPortionGrams);
            final String _tmpImageUri;
            if (_cursor.isNull(_cursorIndexOfImageUri)) {
              _tmpImageUri = null;
            } else {
              _tmpImageUri = _cursor.getString(_cursorIndexOfImageUri);
            }
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final boolean _tmpIsAiAnalyzed;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsAiAnalyzed);
            _tmpIsAiAnalyzed = _tmp != 0;
            _item = new FoodLog(_tmpId,_tmpMealType,_tmpFoodName,_tmpCalories,_tmpProteinG,_tmpCarbsG,_tmpFatG,_tmpPortionGrams,_tmpImageUri,_tmpDate,_tmpTimestamp,_tmpIsAiAnalyzed);
            _result.add(_item);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
