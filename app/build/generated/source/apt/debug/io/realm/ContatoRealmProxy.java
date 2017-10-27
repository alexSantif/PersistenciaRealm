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
public class ContatoRealmProxy extends com.br.persistenciarealm.Contato
    implements RealmObjectProxy, ContatoRealmProxyInterface {

    static final class ContatoColumnInfo extends ColumnInfo {
        long nomeIndex;
        long fotoIndex;

        ContatoColumnInfo(SharedRealm realm, Table table) {
            super(2);
            this.nomeIndex = addColumnDetails(table, "nome", RealmFieldType.STRING);
            this.fotoIndex = addColumnDetails(table, "foto", RealmFieldType.BINARY);
        }

        ContatoColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new ContatoColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final ContatoColumnInfo src = (ContatoColumnInfo) rawSrc;
            final ContatoColumnInfo dst = (ContatoColumnInfo) rawDst;
            dst.nomeIndex = src.nomeIndex;
            dst.fotoIndex = src.fotoIndex;
        }
    }

    private ContatoColumnInfo columnInfo;
    private ProxyState<com.br.persistenciarealm.Contato> proxyState;
    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("nome");
        fieldNames.add("foto");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    ContatoRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (ContatoColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.br.persistenciarealm.Contato>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
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
    public byte[] realmGet$foto() {
        proxyState.getRealm$realm().checkIfValid();
        return (byte[]) proxyState.getRow$realm().getBinaryByteArray(columnInfo.fotoIndex);
    }

    @Override
    public void realmSet$foto(byte[] value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.fotoIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setBinaryByteArray(columnInfo.fotoIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.fotoIndex);
            return;
        }
        proxyState.getRow$realm().setBinaryByteArray(columnInfo.fotoIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Contato");
        builder.addProperty("nome", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addProperty("foto", RealmFieldType.BINARY, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
         return expectedObjectSchemaInfo;
    }

    public static ContatoColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_Contato")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Contato' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_Contato");
        final long columnCount = table.getColumnCount();
        if (columnCount != 2) {
            if (columnCount < 2) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 2 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 2 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 2 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final ContatoColumnInfo columnInfo = new ContatoColumnInfo(sharedRealm, table);

        if (table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key defined for field " + table.getColumnName(table.getPrimaryKey()) + " was removed.");
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
        if (!columnTypes.containsKey("foto")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'foto' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("foto") != RealmFieldType.BINARY) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'byte[]' for field 'foto' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.fotoIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'foto' is required. Either set @Required to field 'foto' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_Contato";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.br.persistenciarealm.Contato createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.br.persistenciarealm.Contato obj = realm.createObjectInternal(com.br.persistenciarealm.Contato.class, true, excludeFields);
        if (json.has("nome")) {
            if (json.isNull("nome")) {
                ((ContatoRealmProxyInterface) obj).realmSet$nome(null);
            } else {
                ((ContatoRealmProxyInterface) obj).realmSet$nome((String) json.getString("nome"));
            }
        }
        if (json.has("foto")) {
            if (json.isNull("foto")) {
                ((ContatoRealmProxyInterface) obj).realmSet$foto(null);
            } else {
                ((ContatoRealmProxyInterface) obj).realmSet$foto(JsonUtils.stringToBytes(json.getString("foto")));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.br.persistenciarealm.Contato createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.br.persistenciarealm.Contato obj = new com.br.persistenciarealm.Contato();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("nome")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ContatoRealmProxyInterface) obj).realmSet$nome(null);
                } else {
                    ((ContatoRealmProxyInterface) obj).realmSet$nome((String) reader.nextString());
                }
            } else if (name.equals("foto")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((ContatoRealmProxyInterface) obj).realmSet$foto(null);
                } else {
                    ((ContatoRealmProxyInterface) obj).realmSet$foto(JsonUtils.stringToBytes(reader.nextString()));
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.br.persistenciarealm.Contato copyOrUpdate(Realm realm, com.br.persistenciarealm.Contato object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.br.persistenciarealm.Contato) cachedRealmObject;
        }

        return copy(realm, object, update, cache);
    }

    public static com.br.persistenciarealm.Contato copy(Realm realm, com.br.persistenciarealm.Contato newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.br.persistenciarealm.Contato) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.br.persistenciarealm.Contato realmObject = realm.createObjectInternal(com.br.persistenciarealm.Contato.class, false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        ContatoRealmProxyInterface realmObjectSource = (ContatoRealmProxyInterface) newObject;
        ContatoRealmProxyInterface realmObjectCopy = (ContatoRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$nome(realmObjectSource.realmGet$nome());
        realmObjectCopy.realmSet$foto(realmObjectSource.realmGet$foto());
        return realmObject;
    }

    public static long insert(Realm realm, com.br.persistenciarealm.Contato object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.br.persistenciarealm.Contato.class);
        long tableNativePtr = table.getNativePtr();
        ContatoColumnInfo columnInfo = (ContatoColumnInfo) realm.schema.getColumnInfo(com.br.persistenciarealm.Contato.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        String realmGet$nome = ((ContatoRealmProxyInterface) object).realmGet$nome();
        if (realmGet$nome != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nomeIndex, rowIndex, realmGet$nome, false);
        }
        byte[] realmGet$foto = ((ContatoRealmProxyInterface) object).realmGet$foto();
        if (realmGet$foto != null) {
            Table.nativeSetByteArray(tableNativePtr, columnInfo.fotoIndex, rowIndex, realmGet$foto, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.br.persistenciarealm.Contato.class);
        long tableNativePtr = table.getNativePtr();
        ContatoColumnInfo columnInfo = (ContatoColumnInfo) realm.schema.getColumnInfo(com.br.persistenciarealm.Contato.class);
        com.br.persistenciarealm.Contato object = null;
        while (objects.hasNext()) {
            object = (com.br.persistenciarealm.Contato) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            String realmGet$nome = ((ContatoRealmProxyInterface) object).realmGet$nome();
            if (realmGet$nome != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nomeIndex, rowIndex, realmGet$nome, false);
            }
            byte[] realmGet$foto = ((ContatoRealmProxyInterface) object).realmGet$foto();
            if (realmGet$foto != null) {
                Table.nativeSetByteArray(tableNativePtr, columnInfo.fotoIndex, rowIndex, realmGet$foto, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.br.persistenciarealm.Contato object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.br.persistenciarealm.Contato.class);
        long tableNativePtr = table.getNativePtr();
        ContatoColumnInfo columnInfo = (ContatoColumnInfo) realm.schema.getColumnInfo(com.br.persistenciarealm.Contato.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        String realmGet$nome = ((ContatoRealmProxyInterface) object).realmGet$nome();
        if (realmGet$nome != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nomeIndex, rowIndex, realmGet$nome, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nomeIndex, rowIndex, false);
        }
        byte[] realmGet$foto = ((ContatoRealmProxyInterface) object).realmGet$foto();
        if (realmGet$foto != null) {
            Table.nativeSetByteArray(tableNativePtr, columnInfo.fotoIndex, rowIndex, realmGet$foto, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.fotoIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.br.persistenciarealm.Contato.class);
        long tableNativePtr = table.getNativePtr();
        ContatoColumnInfo columnInfo = (ContatoColumnInfo) realm.schema.getColumnInfo(com.br.persistenciarealm.Contato.class);
        com.br.persistenciarealm.Contato object = null;
        while (objects.hasNext()) {
            object = (com.br.persistenciarealm.Contato) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            String realmGet$nome = ((ContatoRealmProxyInterface) object).realmGet$nome();
            if (realmGet$nome != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nomeIndex, rowIndex, realmGet$nome, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nomeIndex, rowIndex, false);
            }
            byte[] realmGet$foto = ((ContatoRealmProxyInterface) object).realmGet$foto();
            if (realmGet$foto != null) {
                Table.nativeSetByteArray(tableNativePtr, columnInfo.fotoIndex, rowIndex, realmGet$foto, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.fotoIndex, rowIndex, false);
            }
        }
    }

    public static com.br.persistenciarealm.Contato createDetachedCopy(com.br.persistenciarealm.Contato realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.br.persistenciarealm.Contato unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.br.persistenciarealm.Contato();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.br.persistenciarealm.Contato) cachedObject.object;
            }
            unmanagedObject = (com.br.persistenciarealm.Contato) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        ContatoRealmProxyInterface unmanagedCopy = (ContatoRealmProxyInterface) unmanagedObject;
        ContatoRealmProxyInterface realmSource = (ContatoRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$nome(realmSource.realmGet$nome());
        unmanagedCopy.realmSet$foto(realmSource.realmGet$foto());
        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Contato = proxy[");
        stringBuilder.append("{nome:");
        stringBuilder.append(realmGet$nome() != null ? realmGet$nome() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{foto:");
        stringBuilder.append(realmGet$foto() != null ? realmGet$foto() : "null");
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
        ContatoRealmProxy aContato = (ContatoRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aContato.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aContato.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aContato.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
