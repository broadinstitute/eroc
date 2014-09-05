package org.broadinstitute.eln



import org.junit.*
import grails.test.mixin.*

@TestFor(ApplicationSharedValuesController)
@Mock(ApplicationSharedValues)
class ApplicationSharedValuesControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/applicationSharedValues/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.applicationSharedValuesInstanceList.size() == 0
        assert model.applicationSharedValuesInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.applicationSharedValuesInstance != null
    }

    void testSave() {
        controller.save()

        assert model.applicationSharedValuesInstance != null
        assert view == '/applicationSharedValues/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/applicationSharedValues/show/1'
        assert controller.flash.message != null
        assert ApplicationSharedValues.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/applicationSharedValues/list'


        populateValidParams(params)
        def applicationSharedValues = new ApplicationSharedValues(params)

        assert applicationSharedValues.save() != null

        params.id = applicationSharedValues.id

        def model = controller.show()

        assert model.applicationSharedValuesInstance == applicationSharedValues
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/applicationSharedValues/list'


        populateValidParams(params)
        def applicationSharedValues = new ApplicationSharedValues(params)

        assert applicationSharedValues.save() != null

        params.id = applicationSharedValues.id

        def model = controller.edit()

        assert model.applicationSharedValuesInstance == applicationSharedValues
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/applicationSharedValues/list'

        response.reset()


        populateValidParams(params)
        def applicationSharedValues = new ApplicationSharedValues(params)

        assert applicationSharedValues.save() != null

        // test invalid parameters in update
        params.id = applicationSharedValues.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/applicationSharedValues/edit"
        assert model.applicationSharedValuesInstance != null

        applicationSharedValues.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/applicationSharedValues/show/$applicationSharedValues.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        applicationSharedValues.clearErrors()

        populateValidParams(params)
        params.id = applicationSharedValues.id
        params.version = -1
        controller.update()

        assert view == "/applicationSharedValues/edit"
        assert model.applicationSharedValuesInstance != null
        assert model.applicationSharedValuesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/applicationSharedValues/list'

        response.reset()

        populateValidParams(params)
        def applicationSharedValues = new ApplicationSharedValues(params)

        assert applicationSharedValues.save() != null
        assert ApplicationSharedValues.count() == 1

        params.id = applicationSharedValues.id

        controller.delete()

        assert ApplicationSharedValues.count() == 0
        assert ApplicationSharedValues.get(applicationSharedValues.id) == null
        assert response.redirectedUrl == '/applicationSharedValues/list'
    }
}
