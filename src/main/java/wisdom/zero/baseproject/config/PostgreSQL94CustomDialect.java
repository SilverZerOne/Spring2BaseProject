package wisdom.zero.baseproject.config;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.dialect.PostgreSQL94Dialect;

import java.sql.Types;

public class PostgreSQL94CustomDialect extends PostgreSQL94Dialect {

    public PostgreSQL94CustomDialect() {
        super();
        this.registerHibernateType(Types.ARRAY, ListArrayType.class.getName());
        this.registerHibernateType(Types.ARRAY, StringArrayType.class.getName());
    }
}
