package mdns.v2.records

import mdns.v2.records.structure.IncompleteRecord
import mdns.v2.records.structure.RecordType

/**
 * Created by Mihael Valentin Berčič
 * on 21/12/2020 at 12:13
 * using IntelliJ IDEA
 */
class QueryRecord(override val label: String, override val type: RecordType = RecordType.PTR) : IncompleteRecord {
    override val hasProperty: Boolean = true
}