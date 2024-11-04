package com.si.apirest.projection;

import java.time.LocalDateTime;

public interface VersionView {
    int getId();
    String getArchivoUrl();
    LocalDateTime getFechaVersion();
}
