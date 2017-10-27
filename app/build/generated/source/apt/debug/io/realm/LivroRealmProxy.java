package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class LivroRealmProxy extends com.br.persistenciarealm.Livro
    implements RealmObjectProxy, LivroRealmProxyInterface {

    static final class LivroColumnInfo extends ColumnInfo {
        long idIndex;
        long autorIndex;
        long nomeIndex;
        long anoIndex;

        LivroColumnInfo(SharedRealm realm, Table table) {
            super(4);
            this.idIndex = addColumnDetails(table, "id", RealmFieldType.INTEGER);
            this.autorIndex = addColumnDetails(table, "autor", RealmFieldType.STRING);
            this.nomeIndex = addColumnDetails(table, "nome", RealmFieldType.STRING);
            this.anoIndex = addColumnDetails(table, "ano", RealmFieldType.INTEGER);
        }

        LivroColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new LivroColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final LivroColumnInfo src = (LivroColumnInfo) rawSrc;
            final LivroColumnInfo dst = (LivroColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.autorIndex = src.autorIndex;
            dst.nomeIndex = src.nomeIndex;
            dst.anoIndex = src.anoIndex;
        }
    }

    private LivroColumnInfo columnInfo;
    private ProxyState<com.br.persistenciarealm.Livro> proxyState;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("autor");
        fieldNames.add("nome");
        fieldNames.add("ano");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    LivroRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (LivroColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.br.persistenciarealm.Livro>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idIndex);
    }

    @Override
    public void realmSet$id(int value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$autor() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.autorIndex);
    }

    @Override
    public void realmSet$autor(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.autorIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.autorIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.autorIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.autorIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$nome() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nomeIndex);
    }

    @Override
    public void realmSet$nome(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nomeIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nomeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nomeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nomeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$ano() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.anoIndex);
    }

    @Override
    public void realmSet$ano(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.anoIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.anoIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Livro");
        builder.addProperty("id", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addProperty("autor", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addProperty("nome", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addProperty("ano", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
         return expectedObjectSchemaInfo;
    }

    public static LivroColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_Livro")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Livro' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_Livro");
        final long columnCount = table.getColumnCount();
        if (columnCount != 4) {
            if (columnCount < 4) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 4 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 4 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 4 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final LivroColumnInfo columnInfo = new LivroColumnInfo(sharedRealm, table);

        if (!table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary key not defined for field 'id' in existing Realm file. @PrimaryKey was added.");
        } else {
            if (table.getPrimaryKey() != columnInfo.idIndex) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key annotation definition was changed, from field " + table.getColumnName(table.getPrimaryKey()) + " to field id");
            }
        }

        if (!columnTypes.containsKey("id")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("id") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'id' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.idIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'id' does support null values in the existing Realm file. Use corresponding boxed type for field 'id' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
        }
        if (!columnTypes.containsKey("autor")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'autor' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("autor") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'autor' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.autorIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'autor' is required. Either set @Required to field 'autor' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("nome")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'nome' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("nome") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'nome' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.nomeIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'nome' is required. Either set @Required to field 'nome' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("ano")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'ano' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("ano") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'ano' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.anoIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'ano' does support null values in the existing Realm file. Use corresponding boxed type for field 'ano' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_Livro";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.br.persistenciarealm.Livro createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.br.persistenciarealm.Livro obj = null;
        if (update) {
            Table table = realm.getTable(com.br.persistenciarealm.Livro.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.br.persistenciarealm.Livro.class), false, Collections.<String> emptyList());
                    obj = new io.realm.LivroRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.LivroRealmProxy) realm.createObjectInternal(com.br.persistenciarealm.Livro.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.LivroRealmProxy) realm.createObjectInternal(com.br.persistenciarealm.Livro.class, json.getInt("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }
        if (json.has("autor")) {
            if (json.isNull("autor")) {
                ((LivroRealmProxyInterface) obj).realmSet$autor(null);
            } else {
                ((LivroRealmProxyInterface) obj).realmSet$autor((String) json.getString("autor"));
            }
        }
        if (json.has("nome")) {
            if (json.isNull("nome")) {
                ((LivroRealmProxyInterface) obj).realmSet$nome(null);
            } else {
                ((LivroRealmProxyInterface) obj).realmSet$nome((String) json.getString("nome"));
            }
        }
        if (json.has("ano")) {
            if (json.isNull("ano")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'ano' to null.");
            } else {
                ((LivroRealmProxyInterface) obj).realmSet$ano((int) json.getInt("ano"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.br.persistenciarealm.Livro createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        com.br.persistenciarealm.Livro obj = new com.br.persistenciarealm.Livro();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                } else {
                    ((LivroRealmProxyInterface) obj).realmSet$id((int) reader.nextInt());
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("autor")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LivroRealmProxyInterface) obj).realmSet$autor(null);
                } else {
                    ((LivroRealmProxyInterface) obj).realmSet$autor((String) reader.nextString());
                }
            } else if (name.equals("nome")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((LivroRealmProxyInterface) obj).realmSet$nome(null);
                } else {
                    ((LivroRealmProxyInterface) obj).realmSet$nome((String) reader.nextString());
                }
            } else if (name.equals("ano")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'ano' to null.");
                } else {
                    ((LivroRealmProxyInterface) obj).realmSet$ano((int) reader.nextInt());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.br.persistenciarealm.Livro copyOrUpdate(Realm realm, com.br.persistenciarealm.Livro object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.br.persistenciarealm.Livro) cachedRealmObject;
        }

        com.br.persistenciarealm.Livro realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.br.persistenciarealm.Livro.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = table.findFirstLong(pkColumnIndex, ((LivroRealmProxyInterface) object).realmGet$id());
            if (rowIndex != Table.NO_MATCH) {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.schema.getColumnInfo(com.br.persistenciarealm.Livro.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.LivroRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            } else {
                canUpdate = false;
            }
        }

        if (canUpdate) {
            return update(realm, realmObject, object, cache);
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static com.br.persistenciarealm.Livro copy(Realm realm, com.br.persistenciarealm.Livro newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.br.persistenciarealm.Livro) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.br.persistenciarealm.Livro realmObject = realm.createObjectInternal(com.br.persistenciarealm.Livro.class, ((LivroRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        LivroRealmProxyInterface realmObjectSource = (LivroRealmProxyInterface) newObject;
        LivroRealmProxyInterface realmObjectCopy = (LivroRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$autor(realmObjectSource.realmGet$autor());
        realmObjectCopy.realmSet$nome(realmObjectSource.realmGet$nome());
        realmObjectCopy.realmSet$ano(realmObjectSource.realmGet$ano());
        return realmObject;
    }

    public static long insert(Realm realm, com.br.persistenciarealm.Livro object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.br.persistenciarealm.Livro.class);
        long tableNativePtr = table.getNativePtr();
        LivroColumnInfo columnInfo = (LivroColumnInfo) realm.schema.getColumnInfo(com.br.persistenciarealm.Livro.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((LivroRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((LivroRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, ((LivroRealmProxyInterface) object).realmGet$id());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$autor = ((LivroRealmProxyInterface) object).realmGet$autor();
        if (realmGet$autor != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.autorIndex, rowIndex, realmGet$autor, false);
        }
        String realmGet$nome = ((LivroRealmProxyInterface) object).realmGet$nome();
        if (realmGet$nome != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nomeIndex, rowIndex, realmGet$nome, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.anoIndex, rowIndex, ((LivroRealmProxyInterface) object).realmGet$ano(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.br.persistenciarealm.Livro.class);
        long tableNativePtr = table.getNativePtr();
        LivroColumnInfo columnInfo = (LivroColumnInfo) realm.schema.getColumnInfo(com.br.persistenciarealm.Livro.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.br.persistenciarealm.Livro object = null;
        while (objects.hasNext()) {
            object = (com.br.persistenciarealm.Livro) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((LivroRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((LivroRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, ((LivroRealmProxyInterface) object).realmGet$id());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$autor = ((LivroRealmProxyInterface) object).realmGet$autor();
            if (realmGet$autor != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.autorIndex, rowIndex, realmGet$autor, false);
            }
            String realmGet$nome = ((LivroRealmProxyInterface) object).realmGet$nome();
            if (realmGet$nome != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nomeIndex, rowIndex, realmGet$nome, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.anoIndex, rowIndex, ((LivroRealmProxyInterface) object).realmGet$ano(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.br.persistenciarealm.Livro object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.br.persistenciarealm.Livro.class);
        long tableNativePtr = table.getNativePtr();
        LivroColumnInfo columnInfo = (LivroColumnInfo) realm.schema.getColumnInfo(com.br.persistenciarealm.Livro.class);
        long pkColumnIndex = table.getPrimaryKey();
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((LivroRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((LivroRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, ((LivroRealmProxyInterface) object).realmGet$id());
        }
        cache.put(object, rowIndex);
        String realmGet$autor = ((LivroRealmProxyInterface) object).realmGet$autor();
        if (realmGet$autor != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.autorIndex, rowIndex, realmGet$autor, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.autorIndex, rowIndex, false);
        }
        String realmGet$nome = ((LivroRealmProxyInterface) object).realmGet$nome();
        if (realmGet$nome != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nomeIndex, rowIndex, realmGet$nome, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nomeIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.anoIndex, rowIndex, ((LivroRealmProxyInterface) object).realmGet$ano(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.br.persistenciarealm.Livro.class);
        long tableNativePtr = table.getNativePtr();
        LivroColumnInfo columnInfo = (LivroColumnInfo) realm.schema.getColumnInfo(com.br.persistenciarealm.Livro.class);
        long pkColumnIndex = table.getPrimaryKey();
        com.br.persistenciarealm.Livro object = null;
        while (objects.hasNext()) {
            object = (com.br.persistenciarealm.Livro) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((LivroRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((LivroRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, ((LivroRealmProxyInterface) object).realmGet$id());
            }
            cache.put(object, rowIndex);
            String realmGet$autor = ((LivroRealmProxyInterface) object).realmGet$autor();
            if (realmGet$autor != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.autorIndex, rowIndex, realmGet$autor, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.autorIndex, rowIndex, false);
            }
            String realmGet$nome = ((LivroRealmProxyInterface) object).realmGet$nome();
            if (realmGet$nome != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nomeIndex, rowIndex, realmGet$nome, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nomeIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.anoIndex, rowIndex, ((LivroRealmProxyInterface) object).realmGet$ano(), false);
        }
    }

    public static com.br.persistenciarealm.Livro createDetachedCopy(com.br.persistenciarealm.Livro realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.br.persistenciarealm.Livro unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.br.persistenciarealm.Livro();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.br.persistenciarealm.Livro) cachedObject.object;
            }
            unmanagedObject = (com.br.persistenciarealm.Livro) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        LivroRealmProxyInterface unmanagedCopy = (LivroRealmProxyInterface) unmanagedObject;
        LivroRealmProxyInterface realmSource = (LivroRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$autor(realmSource.realmGet$autor());
        unmanagedCopy.realmSet$nome(realmSource.realmGet$nome());
        unmanagedCopy.realmSet$ano(realmSource.realmGet$ano());
        return unmanagedObject;
    }

    static com.br.persistenciarealm.Livro update(Realm realm, com.br.persistenciarealm.Livro realmObject, com.br.persistenciarealm.Livro newObject, Map<RealmModel, RealmObjectProxy> cache) {
        LivroRealmProxyInterface realmObjectTarget = (LivroRealmProxyInterface) realmObject;
        LivroRealmProxyInterface realmObjectSource = (LivroRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$autor(realmObjectSource.realmGet$autor());
        realmObjectTarget.realmSet$nome(realmObjectSource.realmGet$nome());
        realmObjectTarget.realmSet$ano(realmObjectSource.realmGet$ano());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Livro = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{autor:");
        stringBuilder.append(realmGet$autor() != null ? realmGet$autor() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{nome:");
        stringBuilder.append(realmGet$nome() != null ? realmGet$nome() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{ano:");
        stringBuilder.append(realmGet$ano());
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivroRealmProxy aLivro = (LivroRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aLivro.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aLivro.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aLivro.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
