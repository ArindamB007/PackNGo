package com.png.web.controller.api;

import com.png.data.dto.bookingcart.BookingCartDto;
import com.png.data.dto.invoice.InvoiceDto;
import com.png.data.dto.search.InvoiceSearchDto;
import com.png.data.requests.ApplyCouponRequest;
import com.png.data.requests.InvoiceCancellationRequest;
import com.png.data.responses.ApiResponse;
import com.png.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/invoice")
public class InvoiceServiceController extends ApiBase {

    @RequestMapping(value = "/prepare_invoice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> prepareInvoice(@RequestBody BookingCartDto bookingCartDto) {
        try {
            InvoiceDto invoice = invoiceProcessorService.createInvoice(bookingCartDto);
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/process_invoice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> processInvoice(@RequestBody InvoiceDto preInvoice) {
        try {
            InvoiceDto invoice = invoiceProcessorService.processInvoice(preInvoice);
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/process_cancel_invoice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> cancelInvoice(@RequestBody InvoiceCancellationRequest invoiceCancellationRequest) {
        try {
            InvoiceDto invoice = invoiceCancellationService
                    .processCancelInvoice(invoiceCancellationRequest.getIdInvoice().longValue(),
                            invoiceCancellationRequest.getIdCancelledByUser().longValue());
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/prepare_cancel_invoice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> prepareCancelInvoice(@RequestBody InvoiceCancellationRequest
                                                               invoiceCancellationRequest) {
        try {
            InvoiceDto invoice = invoiceCancellationService.prepareCancelInvoice(invoiceCancellationRequest
                    .getIdInvoice().longValue());
            return new ResponseEntity<>(invoice, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/apply_discount_coupon", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> applyDiscountCoupon(@RequestBody ApplyCouponRequest
                                                              applyCouponRequest) {
        try {
            InvoiceDto invoice = couponProcessorService.processDiscountCoupon(applyCouponRequest);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setResponseData(invoice);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity<>(populateErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/remove_discount_coupon", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> removeDiscountCoupon(@RequestBody ApplyCouponRequest
                                                               removeCouponRequest) {
        try {
            InvoiceDto invoice = couponProcessorService.removeDiscountCoupon(removeCouponRequest);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setResponseData(invoice);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (BaseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(populateErrorResponse(e), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/findInvoice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> findInvoice(@RequestBody InvoiceSearchDto invoiceSearch) {
        try {
			/*return new ResponseEntity<>(invoiceSearchService
					.searchInvoiceByUserId(invoiceSearch.getUserId(),"DESC"), HttpStatus.OK);*/
            return new ResponseEntity<>(invoiceSearchService
                    .searchPagedInvoiceByUserId(
                            invoiceSearch.getUserId(), invoiceSearch.getPageNo() - 1, invoiceSearch.getPageSize(),
                            invoiceSearch.getSortDirection()),
                    HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity<>(populateErrorDetails(e), HttpStatus.BAD_REQUEST);
        }
    }
}
