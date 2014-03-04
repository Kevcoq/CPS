package remocar;
import tamago.ExportService;
import tamago.ServiceExportException;

public interface ExportReceiverService extends ExportService {
    ReceiverService exportReceiverService() throws ServiceExportException;
}

